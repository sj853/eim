package com.eim.db;

import java.sql.Connection;
import java.sql.SQLException;

import com.eim.util.PropertiesUtil;
import com.jolbox.bonecp.BoneCP;
import com.jolbox.bonecp.BoneCPConfig;

public class DataBase {

	private static String driverName;
	private static String connectUrl;
	private static String userName;
	private static String passWord;
	private static BoneCP connectionPool;
	private static BoneCPConfig config;
	private static PropertiesUtil pu = new PropertiesUtil("conn.properties");
	private Connection conn;

	static {
		driverName = pu.read("DriverName");
		connectUrl = pu.read("ConnectUrl");
		userName = pu.read("UserName");
		passWord = pu.read("PassWord");

		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// 设置连接池配置信息
		config = new BoneCPConfig();
		// 数据库的JDBC URL
		config.setJdbcUrl(connectUrl);
		// 数据库用户名
		config.setUsername(userName);
		// 数据库用户密码
		config.setPassword(passWord);
		// 数据库连接池的最小连接数
		config.setMinConnectionsPerPartition(5);
		// 数据库连接池的最大连接数
		config.setMaxConnectionsPerPartition(10);

		config.setPartitionCount(1);
		try {
			connectionPool = new BoneCP(config);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnect() {
		try {
			conn = connectionPool.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public void close() {
		try {
			if (!conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
		}
	}

}
