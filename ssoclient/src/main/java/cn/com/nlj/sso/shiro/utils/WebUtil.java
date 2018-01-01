package cn.com.nlj.sso.shiro.utils;
/***
* 类说明：
* @author nlj
* 2018年1月1日 下午11:43:59
*/

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import cn.com.nlj.sso.dto.UserDto;

public class WebUtil {

	public static UserDto userDto() {
		Subject subject = SecurityUtils.getSubject();
		UserDto userDto = (UserDto) subject.getSession().getAttribute("user");
		return userDto;
	}
}
