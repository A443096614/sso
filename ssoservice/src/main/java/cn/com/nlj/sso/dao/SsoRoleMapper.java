package cn.com.nlj.sso.dao;

import org.apache.ibatis.annotations.Param;

import cn.com.nlj.sso.pojo.SsoRole;

public interface SsoRoleMapper {
	
    int insert(SsoRole record);

    int updateById(@Param("record") SsoRole record, @Param("id") Integer id);
}