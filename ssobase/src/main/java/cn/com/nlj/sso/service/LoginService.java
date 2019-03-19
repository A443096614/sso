package cn.com.nlj.sso.service;

import java.util.List;
import java.util.Map;

import cn.com.nlj.sso.dto.RoleDto;
import cn.com.nlj.sso.dto.UserDto;

public interface LoginService {

	/***
	 * 根据用户编号查询用户信息
	 * @param userNo
	 * @return
	 */
	public UserDto queryUserInfoByUserNo(String userNo);
	
	/***
	 * 根据操作员岗位查询菜单
	 * @param userNo
	 * @return
	 */
	public Map<String, Object> queryLeftMenu(List<RoleDto> roleList);
}