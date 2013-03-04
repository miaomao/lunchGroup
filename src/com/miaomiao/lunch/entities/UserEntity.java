package com.miaomiao.lunch.entities;

import java.io.Serializable;
import java.util.Date;

//import com.miaomiao.lunch.util.LaunchConstant;

public class UserEntity implements Serializable {

	private static final long serialVersionUID = 5817351434167221246L;
	private Integer id;
	private String userName;
	private String password = "";
	private Date created = new Date();
	private Date updated = new Date();
	private Date lastLogin = new Date();
	private Float leftOver = 0.0f;
	private Integer deleted = 0;

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Float getLeftOver() {
		return leftOver;
	}

	public void setLeftOver(Float leftOver) {
		this.leftOver = leftOver;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
}
