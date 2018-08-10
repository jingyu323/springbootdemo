package com.rain.springbootstudy.enity;

import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class UserBean {

	private Integer id;
	private String phone;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	private String name;
	@JSONField(format = "yyyy-MM-dd")
	private Date birthday;
	private String address;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
