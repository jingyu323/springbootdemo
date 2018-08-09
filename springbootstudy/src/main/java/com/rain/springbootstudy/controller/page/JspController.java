package com.rain.springbootstudy.controller.page;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("jsp")
public class JspController {

	@RequestMapping("/hello")
	public String getListaUtentiView(ModelMap map) {
		map.put("name", "Spring Boot");
		map.put("msg", "Rain first  jsp demo");
		return "hello";
	}

}
