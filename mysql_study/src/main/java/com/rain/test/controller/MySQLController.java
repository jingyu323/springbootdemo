package com.rain.test.controller;


import com.rain.test.bean.UserBean;
import com.rain.test.dao.impl.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MySQLController {
    @Autowired
    private UserDaoImpl userDaoImpl;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public List<UserBean> findAll() {
        List<UserBean> list = userDaoImpl.findall();
        for (UserBean user : list) {
            System.out.println(user.getName() + " " + user.getAge());
        }


        return list;
    }


    @RequestMapping(value = "/allByPartion", method = RequestMethod.GET)
    public List<UserBean> allByPartion(String pation) {
        List<UserBean> list = userDaoImpl.findallByPartion(pation);
        return list;
    }


    @RequestMapping(value = "/this", method = RequestMethod.GET)
    public String findByname(String username) {
        username = "李四";
        UserBean user;
        user = userDaoImpl.findByname(username);
        System.out.println(user.getName() + " " + user.getAge());
        return "传参查询";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(UserBean user) {//@ModelAttribute("user") User user
        user.setName("海龟");
        user.setAge("21");
        System.out.println(user.getName() + "接收参数.....");
        boolean flag = userDaoImpl.addUser(user);
        System.out.println("......" + flag + ".....");
        return "添加";
    }


    @RequestMapping(value = "/del", method = RequestMethod.GET)
    public String deleteByUser(String name) {
        name = "海龟";
        boolean flag = userDaoImpl.deleteByName(name);
        System.out.println(flag);
        return "根据name删除";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(String name, String age) {
        //指定名字
        name = "李四";
        //要修改的age
        age = "13";
        System.out.println(userDaoImpl.findByname(name).getName());
        System.out.println(userDaoImpl.updateByName(name, age));
        System.out.println(userDaoImpl.findByname(name).getAge());
        return "修改数据";
    }

}
