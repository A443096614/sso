package cn.com.nlj.sso.shiro;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;

import cn.com.nlj.sso.dto.UserDto;
import cn.com.nlj.sso.exception.BusinessException;
import cn.com.nlj.sso.service.RemoteApi;
import cn.com.nlj.sso.service.RemoteService;

/***
 * 类说明：权限管理
 * 
 * @author nlj 2017年12月10日 下午9:12:03
 */
public class MyShiroRealm extends AuthorizingRealm {
	
	@Resource
	private RemoteService remoteService;
	
	/***
	 * 认证信息(授权)
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// 1. 从 PrincipalCollection 中来获取登录用户的信息
		Object principal = principals.getPrimaryPrincipal();
		// 2. 利用登录的用户的信息来用户当前用户的角色或权限(可能需要查询数据库)
		Set<String> roles = new HashSet<>();
		roles.add("user");
		if ("admin".equals(principal)) {
			roles.add("admin");
		}
		// 3. 创建 SimpleAuthorizationInfo, 并设置其 reles 属性.
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

		// 4. 返回 SimpleAuthorizationInfo 对象.
		return info;
	}

	/***
	 * 认证信息(身份验证)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 1. 把 AuthenticationToken 转换为 UsernamePasswordToken
		UsernamePasswordToken upToken = (UsernamePasswordToken) token;

		// 2. 从 UsernamePasswordToken 中来获取 username
		String userNo = upToken.getUsername();

		// 3. 调用数据库的方法, 从数据库中查询 username 对应的用户记录
		UserDto userDto = null;
		try {
			userDto = (UserDto) remoteService.getRemotService(RemoteApi.LOGINSERVICE, "queryUserInfoByUserNo", userNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException("登录失败，请重试！");
		}
		// 4. 若用户不存在, 则可以抛出 UnknownAccountException 异常
		if (userDto == null) {
			throw new UnknownAccountException("输入的用户名不存在，请重新输入！");
		}

		// 5. 根据用户信息的情况, 决定是否需要抛出其他的 AuthenticationException 异常.
		if ("1".equals(userDto.getIsLock())) {
			throw new LockedAccountException("该用户已锁定，请等会再登录！");
		}

		// 6. 根据用户的情况, 来构建 AuthenticationInfo 对象并返回. 通常使用的实现类为: SimpleAuthenticationInfo
		// 以下信息是从数据库中获取的.
		// 1). principal: 认证的实体信息. 可以是 username, 也可以是数据表对应的用户的实体类对象.
		Object principal = userNo;
		// 2). credentials: 密码.
		Object credentials = userDto.getPassWord();
		// 3). realmName: 当前 realm 对象的 name. 调用父类的 getName() 方法即可
		String realmName = getName();
		// 4). 盐值.
		ByteSource credentialsSalt = ByteSource.Util.bytes(userNo);

		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(principal, credentials, credentialsSalt, realmName);
		Subject subject = SecurityUtils.getSubject();
		//5).将userDto里的密码置空
		userDto.setPassWord("");
		subject.getSession().setAttribute("user", userDto);
		return info;
	}

	public static void main(String[] args) {
		String hashAlgorithmName = "SHA1";
		Object credentials = "admin";
		Object salt = ByteSource.Util.bytes("admin");;
		int hashIterations = 1024;
		
		Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
		System.out.println(result);
	}	
}
