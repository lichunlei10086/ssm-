package com.study.demo.dao;

import com.study.demo.model.User;

public interface UserMapper {

	int insert(User u);

	User selectById(Long id);
}
