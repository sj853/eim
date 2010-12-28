package com.eim.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.regex.Pattern;

import com.eim.beans.Department;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonFormat<T> {

	String gs;
	String rapl;
	Pattern getM;
	public JsonFormat() {
		gs = "get(\\w+)";
		getM = Pattern.compile(gs);
		rapl = "$1";
		String tag;
	}

	public JSONArray format(ArrayList<T> ts) {

		JSONArray jarray = new JSONArray();
		for (T t : ts) {
			JSONObject jobject = new JSONObject();
			Field[] fields = t.getClass().getDeclaredFields();
			for (Field field : fields) {
				try {
					jobject.element(field.getName(), field.get(t).toString());
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			jarray.add(jobject);
		}
		return jarray;

	}

	public static void main(String[] args) {
		// Department dept = new Department();
		// dept.setId(1);
		// dept.setName("sdafsdf");
		// dept.setSuperid(1);
		// ArrayList<Department> depts = new ArrayList<Department>();
		// depts.add(dept);
		// JSONArray jarry = new JsonFormat<Department>().format(depts);
		// System.out.println(jarry.isEmpty());

		JSONObject jobject = new JSONObject();
		Method[] methods = Department.class.getDeclaredMethods();
		for (Method method : methods) {
			try {
				System.out.println(method.getName());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
	}
}
