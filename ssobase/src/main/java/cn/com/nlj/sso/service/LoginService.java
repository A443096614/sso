package cn.com.nlj.sso.service;

import cn.com.nlj.sso.dto.UserDto;

public interface LoginService {

	/***
	 * �����û���Ų�ѯ�û���Ϣ
	 * @param userNo
	 * @return
	 */
	public UserDto queryUserInfoByUserNo(String userNo);
}
