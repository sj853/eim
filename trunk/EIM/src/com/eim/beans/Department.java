package com.eim.beans;

/**
 * Department实体类
 * @author element
 *
 */
public class Department {

	private int id;
	private String name;
	private int superid;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public int getSuperid() {
		return superid;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSuperid(int superid) {
		this.superid = superid;
	}
}
