package com.eim.beans;

/**
 * Department实体类
 * @author element
 *
 */
public class Department {

	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSuperid() {
		return superid;
	}
	public void setSuperid(int superid) {
		this.superid = superid;
	}
	private int superid;
}
