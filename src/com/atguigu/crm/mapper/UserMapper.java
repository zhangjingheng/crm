package com.atguigu.crm.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.atguigu.crm.entity.User;

public interface UserMapper {
	@Select("select u.name,u.password,u.enabled,r.name as \"role.name\" from users u left outer join roles r on role_id=r.id where u.name=#{name}")
	User getUser(@Param("name") String name);
}
