package com.eim.test.dbconn;


import com.eim.db.DataBase;
import com.eim.util.PropertiesUtil;

public class TestDB {

	public static void main(String[] args) {
		new TestDB().test(null);
	}
	
	public void test(String path){
		DataBase db = new DataBase();
	}
}
