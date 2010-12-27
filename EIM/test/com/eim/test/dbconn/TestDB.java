package com.eim.test.dbconn;

import com.eim.util.PropertiesUtil;

public class TestDB {

	public static void main(String[] args) {
		PropertiesUtil pu  = new PropertiesUtil("./WebRoot/conf/admin.properties");
		System.out.println(pu.read("password"));
	}
}
