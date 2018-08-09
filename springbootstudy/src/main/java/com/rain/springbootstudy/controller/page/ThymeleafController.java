package com.rain.springbootstudy.controller.page;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("thymeleaf")
public class ThymeleafController {

	@RequestMapping("hello")

	public String hello(Map<String, Object> map) {
		map.put("msg", "Rain first  thymeleaf demo");
		return "hello";
	}

}
