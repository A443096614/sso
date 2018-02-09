package cn.com.nlj.sso.service;

import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.nlj.sso.dao.SsoUserMapper;
import cn.com.nlj.sso.dto.RoleDto;
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
		//List<SsoRole> roleList = ssoUser.getRoleList();
		UserDto udto = new UserDto();
		dozerBeanMapper.map(ssoUser, udto);
		return udto;
	}

	@Override
	public List<Map<String, Object>> queryLeftMenu(List<RoleDto> roleList) {
		for (RoleDto roleDto : roleList) {
			Integer roleId = roleDto.getId();
		}
		return null;
	}

}
