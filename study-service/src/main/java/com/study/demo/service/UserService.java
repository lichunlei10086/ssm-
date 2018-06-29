package com.study.demo.service;

import com.study.demo.model.User;

public interface UserService {
	
	
	int insert(User u);

	User selectById(Long id);

}
