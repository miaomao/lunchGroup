package com.miaomiao.lunch.entities;

import java.io.Serializable;
import java.util.Date;

public class ExpenseTypeEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6367490220241362812L;
	private Integer id;
	private String name;
	private String attr;
	private Date created = new Date();
	private Integer deleted = 0;

	public Integer getDeleted() {
		return deleted;
	}

	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getAttr() {
		return attr;
	}

	public void setAttr(String attr) {
		this.attr = attr;
	}

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
}
