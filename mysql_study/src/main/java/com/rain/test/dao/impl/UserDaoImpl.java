package com.rain.test.dao.impl;

import com.rain.test.bean.UserBean;
import com.rain.test.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;


import java.util.List;


@Service("userDaoImpl")
public class UserDaoImpl implements UserDao {

    @Autowired //是用在JavaBean中的注解，通过byType形式，用来给指定的字段或方法注入所需的外部资源。
    private JdbcTemplate jdbcTemplate; //jdbc连接工具类

    @Override
    public List<UserBean> findall() {
        String sql = "select * from test_user";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserBean>(UserBean.class));
    }

    @Override
    public UserBean findByname(String name) {
        String sql = "select * from test_user where name=?";
        Object[] params = new Object[]{name};
        return jdbcTemplate.queryForObject(
                sql,
                params,
                new BeanPropertyRowMapper<>(UserBean.class));
    }

    @Override
    public boolean addUser(UserBean user) {
        String sql = "insert into test_user(name,age)values(?,?)";
        Object[] params = {user.getName(), user.getAge()};
        return jdbcTemplate.update(sql, params) > 0;
    }

    @Override
    public boolean updateByName(String name, String age) {
        String sql = "update test_user set age=? where name=?";
        Object[] params = {name, age};
        return jdbcTemplate.update(sql, params) > 0;
    }

    @Override
    public boolean deleteByName(String name) {
        String sql = "delete from test_user where name=?";
        Object[] params = {name};
        return jdbcTemplate.update(sql, params) > 0;
    }

    @Override
    public List<UserBean> findallByPartion(String pationNumber) {
        String sql = "select * from test_user partition(" + pationNumber + ")";

        System.out.println(sql);
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserBean>(UserBean.class));
    }
}
