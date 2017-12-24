package cn.com.nlj.sso.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.com.nlj.sso.shiro.MyShiroRealm;

/***
* 类说明：
* @author nlj
* 2017年12月10日 下午5:24:09
*/
@Configuration
public class ShiroConfig {
	
	@Bean(name="ehCacheManager")
    public EhCacheManager getEhCacheManager() {  
        EhCacheManager em = new EhCacheManager();  
        em.setCacheManagerConfigFile("classpath:ehcache-shiro.xml");  
        return em;  
    }
	
	@Bean(name = "myShiroRealm")
	public MyShiroRealm myShiroRealm(@Qualifier("ehCacheManager")EhCacheManager cacheManager) {
		MyShiroRealm realm = new MyShiroRealm();
		realm.setCacheManager(cacheManager);
		realm.setCredentialsMatcher(hashedCredentialsMatcher());
		return realm;
	}
	
	@Bean(name = "securityManager")
	public DefaultWebSecurityManager securityManager(@Qualifier("myShiroRealm") MyShiroRealm myShiroRealm) {
		DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
		dwsm.setRealm(myShiroRealm);
		// <!-- 用户授权/认证信息Cache, 采用EhCache 缓存 -->
		dwsm.setCacheManager(getEhCacheManager());
		return dwsm;
	}
	
	/**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
       HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
       hashedCredentialsMatcher.setHashAlgorithmName("SHA1");//散列算法:这里使用SHA1算法;
       hashedCredentialsMatcher.setHashIterations(1024);//散列的次数，比如散列两次，相当于 SHA1(SHA1(""));
       return hashedCredentialsMatcher;
    }

	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
		//静态资源文件
		chainDefinition.addPathDefinition("/layui-v2.2.45/**", "anon");//anon游客访问
		chainDefinition.addPathDefinition("/style/**", "anon");
		chainDefinition.addPathDefinition("/js/**", "anon");
		//验证码
		chainDefinition.addPathDefinition("/defaultKaptcha", "anon");
		//登录请求
		chainDefinition.addPathDefinition("/sys/login", "anon");
		//所有访问都通过认证后才可以访问
		chainDefinition.addPathDefinition("/**", "authc");  
		return chainDefinition;
	}
}
