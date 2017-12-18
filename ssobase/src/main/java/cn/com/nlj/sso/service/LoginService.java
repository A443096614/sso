package cn.com.nlj.sso.service;

import cn.com.nlj.sso.dto.UserDto;

public interface LoginService {

	/***
	 * 根据用户编号查询用户信息
	 * @param userNo
	 * @return
	 */
	public UserDto queryUserInfoByUserNo(String userNo);
}