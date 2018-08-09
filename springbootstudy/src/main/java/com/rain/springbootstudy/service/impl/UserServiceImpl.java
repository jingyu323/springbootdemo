package com.rain.springbootstudy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rain.springbootstudy.domain.User;
import com.rain.springbootstudy.domain.UserMapper;
import com.rain.springbootstudy.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapperp;

	public List<User> listAll() {
		return userMapperp.selectAll();
	}

}
