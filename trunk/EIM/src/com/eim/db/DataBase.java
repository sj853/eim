package com.eim.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eim.util.PropertiesUtil;


public class DataBase {
	
	private String driverName;
	private String connectUrl;
	private String userName;
	private String passWord;
	private PropertiesUtil pu;
	public Connection conn;
	
	public DataBase() {
		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection(connectUrl, userName, passWord);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void init(){
		pu = new PropertiesUtil("conn.properties");
		driverName = pu.read("DriverName");
		connectUrl = pu.read("connectUrl");
		userName = pu.read("UserName");
		passWord = pu.read("PassWord");
		
	}
	
	
	
	public ResultSet getResult(String sqlStr,String[] vals){
		ResultSet result = null;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sqlStr);
			for(int i=0;i<vals.length;i++){
				ps.setString(i, vals[i]);
			}
			result = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	
	
}
