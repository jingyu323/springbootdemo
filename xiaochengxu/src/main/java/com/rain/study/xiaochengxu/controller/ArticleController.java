package com.rain.study.xiaochengxu.controller;


import com.rain.study.xiaochengxu.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {


    @RequestMapping("/list")
    @ResponseBody
    public List<User> list(String title, Integer pageSize, Integer pageNum) {

        List<User> list = new ArrayList<User>();

        User  user  =  new User();
        user.setDesc("list desc ");
        user.setAge(23);

        list.add(user);

        return list;
    }
}
