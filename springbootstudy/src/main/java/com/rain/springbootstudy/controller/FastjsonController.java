package com.rain.springbootstudy.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rain.springbootstudy.domain.User;

@Controller
@RequestMapping("fastjson")
public class FastjsonController {

	@RequestMapping("/test")
	@ResponseBody
	public User test() {
		User user = new User();

		user.setId(1);
		user.setName("jack");
		user.setAddress("jack123");
		user.setBirthday(new Date());
		user.setPhone("123121312");

		return user;
	}



}
