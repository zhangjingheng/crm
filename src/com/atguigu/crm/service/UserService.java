package com.atguigu.crm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	@Transactional(readOnly=true)
	public User login(String name ,String password){
		User user = userMapper.getUser(name);
		if(user!=null &&user.getName()!=null&&user.getPassword().equals(password)){
			return user;
		}
		return null;
	}
}
