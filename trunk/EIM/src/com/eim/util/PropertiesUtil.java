package com.eim.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;


public class PropertiesUtil {

	public static String configPath = "D:/Program Files/Apache Software Foundation/Tomcat 6.0/webapps/EIM/WEB-INF/classes/conf/";
	
	public static Properties config;
	private String pName;

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpName() {
		return pName;
	}
	
	
	public PropertiesUtil(String pName) {
		this.pName = pName;
		config = new Properties();
	}
	
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
	
	public void write(String key,String value){
		OutputStream  os = null;
		try {
		    os = new FileOutputStream(getpName());
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
