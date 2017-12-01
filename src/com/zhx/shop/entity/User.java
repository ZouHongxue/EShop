package com.zhx.shop.entity;

public class User {
	
	private String name;
	private String pwd;
	private String email;
	private String nikyname;
	private String sex;
	private String birthday;
	
	public User() {
		super();
	}

	public User(String name, String pwd, String email, String nikyname, String sex, String birthday) {
		super();
		this.name = name;
		this.pwd = pwd;
		this.email = email;
		this.nikyname = nikyname;
		this.sex = sex;
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNikyname() {
		return nikyname;
	}

	public void setNikyname(String nikyname) {
		this.nikyname = nikyname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
}
