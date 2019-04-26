package com.rain.study.xiaochengxu.model;

public class User {
    private  String name;
    private  Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private  String desc;

    public User(String name, Integer age, String desc) {
        this.name = name;
        this.age = age;
        this.desc = desc;
    }

    public User(){}
}
