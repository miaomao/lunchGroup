package com.miaomiao.lunch.entities;

import java.io.Serializable;
import java.util.Date;

public class ExpenseItemEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String eTypeName;
	private String accountName;
	private Date created = new Date();
	private Float value = 0.0f;

	public String geteTypeName() {
		return eTypeName;
	}

	public void seteTypeName(String eTypeName) {
		this.eTypeName = eTypeName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
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