package cn.com.nlj.sso.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.nlj.sso.dao.SsoMenuMapper;
import cn.com.nlj.sso.dao.SsoUserMapper;
import cn.com.nlj.sso.dto.RoleDto;
import cn.com.nlj.sso.dto.UserDto;
import cn.com.nlj.sso.pojo.SsoMenu;
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
	@Autowired
	private SsoMenuMapper ssoMenuMapper;

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

	@Override
	public Map<String, Object> queryLeftMenu(List<RoleDto> roleList) {
		List<Map<String, Object>> menuList = null;
		Map<String, Object> rMap = new LinkedHashMap<>();
		for (RoleDto roleDto : roleList) {
			menuList = new ArrayList<>();
			Integer roleId = roleDto.getId();
			List<SsoMenu> oneMenuList = ssoMenuMapper.selectOneMenuByRoleId(roleId);
			List<SsoMenu> twoMenuList = ssoMenuMapper.selectTwoMenuByRoleId(roleId);
			List<Map<String, Object>> twoList = null;
			Map<String, Object> oneMap = new LinkedHashMap<>();
			for (SsoMenu menu : oneMenuList) {
				twoList = new ArrayList<>();
				Map<String, Object> map = null;
				for (SsoMenu towMenu : twoMenuList) {
					if (menu.getId() == towMenu.getParentId()) {
						map = new LinkedHashMap<>();
						map.put("id", towMenu.getId());
						map.put("name", towMenu.getName());
						map.put("type", towMenu.getType());
						map.put("url", towMenu.getUrl());
						map.put("icon", towMenu.getIcon());
						twoList.add(map);
					}
				}
				oneMap.put("id", menu.getId());
				oneMap.put("name", menu.getName());
				oneMap.put("type", menu.getType());
				oneMap.put("url", menu.getUrl());
				oneMap.put("icon", menu.getIcon());
				oneMap.put("children", twoList);
				menuList.add(oneMap);
			}
			rMap.put("roldid" + roleDto.getId(), menuList);
		}
		return rMap;
	}

}
