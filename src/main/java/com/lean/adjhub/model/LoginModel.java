package com.lean.adjhub.model;

import java.io.Serializable;

public class LoginModel implements Serializable {


	private String company;

	private String group;

	private String user;

	private String password;

	private boolean loginForceRender;

	public LoginModel() {
		super();
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoginForceRender() {
		return loginForceRender;
	}

	public void setLoginForceRender(boolean loginForceRender) {
		this.loginForceRender = loginForceRender;
	}

}