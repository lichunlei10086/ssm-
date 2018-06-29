package com.study.demo.service.impl;

import javax.annotation.Resource;

import com.study.demo.dao.UserMapper;
import com.study.demo.model.User;
import com.study.demo.service.UserService;

import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper;
	
	@Override
	public int insert(User u) {
		return userMapper.insert(u);
	}
	
	@Override
	public User  selectById (Long id) {
		return userMapper.selectById(id);
	}
	
}
