package com.rain.springbootstudy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rain.springbootstudy.domain.User;
import com.rain.springbootstudy.service.UserService;

@RequestMapping("user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/list/all")
	public List<User> listAll() {
		return userService.listAll();
	}

}
