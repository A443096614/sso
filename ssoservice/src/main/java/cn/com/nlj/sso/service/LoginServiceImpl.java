package cn.com.nlj.sso.service;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.nlj.sso.dao.SsoUserMapper;
import cn.com.nlj.sso.dto.UserDto;
import cn.com.nlj.sso.pojo.SsoUser;

/***
* 类说明：
* @author nlj
* 2017年12月16日 下午10:25:58
*/
@Service
public class LoginServiceImpl implements LoginService {
	
	@Autowired
	private Mapper dozerBeanMapper;
	@Autowired
	private SsoUserMapper ssoUserMapper;

	@Override
	public UserDto queryUserInfoByUserNo(String userNo) {
		SsoUser ssoUser = ssoUserMapper.selectByUserNo(userNo);
		if (ssoUser == null) {
			return null;
		}
		UserDto udto = new UserDto();
		dozerBeanMapper.map(ssoUser, udto);
		return udto;
	}

}
