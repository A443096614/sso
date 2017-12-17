package cn.com.nlj.sso.dao;

import org.apache.ibatis.annotations.Param;

import cn.com.nlj.sso.pojo.SsoUser;

public interface SsoUserMapper {
	
	SsoUser selectByUserNo(String userNo);
	
    int insert(SsoUser record);

    int updateByUserNo(@Param("record") SsoUser record, @Param("userNo") String userNo);
}