package com.eim.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.eim.util.PropertiesUtil;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;


public class DataBase {
	
	private String driverName;
	private String connectUrl;
	private String userName;
	private String passWord;
	private PropertiesUtil pu;
	public static BoneCP connectionPool;
	private BoneCPConfig config;
	
	
	
	public DataBase() {
		try {
			init();
			Class.forName(driverName);
			if(connectionPool==null)connectionPool = new BoneCP(config);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
	}
	
	public void init(){
		pu = new PropertiesUtil("conn.properties");
		driverName = pu.read("DriverName");
		connectUrl = pu.read("ConnectUrl");
		userName = pu.read("UserName");
		passWord = pu.read("PassWord");
		
		
	     //设置连接池配置信息
        config = new BoneCPConfig();
        //数据库的JDBC URL
        config.setJdbcUrl(connectUrl); 
        //数据库用户名
        config.setUsername(userName); 
        //数据库用户密码
        config.setPassword(passWord);
        //数据库连接池的最小连接数
        config.setMinConnectionsPerPartition(5);
        //数据库连接池的最大连接数
        config.setMaxConnectionsPerPartition(10);
        
        config.setPartitionCount(1);
	}
	
	
	public ResultSet getResult(String sqlStr,String val){

		ResultSet result = null;
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
			ps = conn.prepareStatement(sqlStr);
			if(!"".equals(val))ps.setString(0, val);
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
	
	public ResultSet getResult(String sqlStr,String[] vals){
		ResultSet result = null;
		PreparedStatement ps = null;
		Connection conn = null;
		try {
			conn = connectionPool.getConnection();
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
	
	public static void main(String[] args) throws SQLException {
		ResultSet rs = new DataBase().getResult("select * from department", "");
		while(rs.next()){
			System.out.println(rs.getString(2));
		}
	}
	
}
