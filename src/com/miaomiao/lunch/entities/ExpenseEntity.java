package com.miaomiao.lunch.entities;

import java.io.Serializable;
import java.util.Date;

public class ExpenseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer eTypeId;
	private Integer userId;
	private Date created;
	private Float value;

	public Integer geteTypeId() {
		return eTypeId;
	}

	public void seteTypeId(Integer eTypeId) {
		this.eTypeId = eTypeId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Float getValue() {
		return value;
	}

	public void setValue(Float value) {
		this.value = value;
	}

}
