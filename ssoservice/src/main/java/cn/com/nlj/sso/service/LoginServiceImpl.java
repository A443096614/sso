package cn.com.nlj.sso.service;

import org.springframework.stereotype.Service;

import cn.com.nlj.sso.dto.UserDto;

/***
* 类说明：
* @author nlj
* 2017年12月16日 下午10:25:58
*/
@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public UserDto queryUserInfoByUserNo(String userNo) {
		System.err.println("userNo=============>>>>");
		return null;
	}

}
