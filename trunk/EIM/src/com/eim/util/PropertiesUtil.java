package com.eim.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

/**
 * 
 * @author element
 *配置文件操作类
 */
public class PropertiesUtil {

	public static String configPath;
	
	public static Properties config;
	private String pName;

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpName() {
		return pName;
	}
	
	static{
		configPath = PropertiesUtil.class.getResource("/conf/").toString().substring(6).replaceAll("%20", " ");
	}
	public PropertiesUtil(String pName) {
		this.pName = pName;
		config = new Properties();
	}
	
	/**
	 * 读取key=X的值
	 * @param key 健
	 * @return 值
	 */
	public String read(String key){
		FileInputStream fis = null;
		String value = null; 
		try {
			fis = new FileInputStream(configPath+getpName());
			config.load(fis);
			value = config.getProperty(key);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return value;
	}
	
	/**
	 * 修改key=X的值
	 * @param key 键
	 * @param value 值
	 */
	public void write(String key,String value){
		OutputStream  os = null;
		try {
		    os = new FileOutputStream(configPath+getpName());
			config.setProperty(key, value);
			config.store(os, null);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
