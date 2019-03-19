package cn.com.nlj.sso.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.com.nlj.sso.pojo.SsoMenu;

public interface SsoMenuMapper {

    int insert(SsoMenu record);

    List<SsoMenu> selectOneMenuByRoleId(int roleId);

    List<SsoMenu> selectTwoMenuByRoleId(int roleId);

    int updateById(@Param("record") SsoMenu record, @Param("id") Integer id);
}