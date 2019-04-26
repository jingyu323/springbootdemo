package com.rain.study.xiaochengxu.controller;


import com.rain.study.xiaochengxu.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/xiaochengxu")
public class TestApiController {


    @RequestMapping(value = "/findall")
    public User getDemoInfo() {

        User user =  new User();
        user.setAge(12);
        user.setName("test demo ");
        user.setDesc("  demo desc ");
        return user;
    }



}
