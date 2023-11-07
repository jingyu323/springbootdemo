package com.rain.test.dao;

import com.rain.test.bean.UserBean;

import java.util.List;

public interface UserDao {
    List<UserBean> findall();

    UserBean findByname(String name);

    boolean addUser(UserBean user);

    boolean updateByName(String name, String age);

    boolean deleteByName(String name);

    List<UserBean> findallByPartion(String pationNumber);

}
