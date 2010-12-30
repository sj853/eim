package com.eim.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Pattern;
import net.sf.json.JSONObject;

/**
 * 
 * @author element
 *将实体类的数组转换成JSON
 *
 * @param <T>
 */
public class JsonFormat<T> {

	static String gs = "get(\\w+)";
	static String rapl = "$1";
	static Pattern getM = Pattern.compile(gs);

	
	
	/**
	 * 转成Json
	 * @param ts
	 * @return
	 */
	public JSONObject format(ArrayList<T> ts,int rows) {
		StringBuilder sb = new StringBuilder();
		JSONObject jObject = new JSONObject();
		sb.append("{total:"+rows+",rows:[");
		for (T t : ts) {
			Method[] methods = t.getClass().getDeclaredMethods();
			sb.append("{");
			for (Method method : methods) {
				String methodName = method.getName();
				String tag;
				try {
					 if (Pattern.matches(gs, methodName)) {
						 tag = getM.matcher(methodName).replaceAll(rapl).toLowerCase();
						 sb.append(tag+":"+ method.invoke(t)+",");
			            }
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			sb.deleteCharAt(sb.length()-1).append("},");
		}
		String str = sb.deleteCharAt(sb.length()-1).append("]}").toString();
		System.out.println(str);
		return JSONObject.fromString(str);
	}

}
