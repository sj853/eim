package com.eim.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.eim.beans.Department;
import com.eim.beans.Employee;
import com.eim.dao.EmployeeDAO;
import com.eim.db.DataBase;

/**
 * 
 * @author element
 *员工信息dao实现类
 */
public class EmployeeDAOImpl implements EmployeeDAO{

	private DataBase db;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public EmployeeDAOImpl() {
		db = new DataBase();
	}
	
	public ArrayList<Employee> getElementByNameAtDept(int dept_id, String name) {
		ArrayList<Employee> emps = new ArrayList<Employee>();
		Connection conn = db.getConnect();
		String sqlStr = "select * from employee where emp_name=? and dept_id=?";
		try {
			ps = conn.prepareStatement(sqlStr);
			ps.setString(1, name);
			ps.setInt(2, dept_id);
			rs = ps.executeQuery();
			while(rs.next()){
				Employee emp = new Employee();
				emp.setId(rs.getString("emp_id"));
				emp.setName(rs.getString("emp_name"));
				emp.setAddress(rs.getString("emp_address"));
				emp.setBirthday(rs.getDate("emp_birthday"));
				emp.setChinese(rs.getString("emp_mandarin_level"));
				emp.setCounty(rs.getString("emp_native_county"));
				emp.setDepid(rs.getInt("dept_id"));
				emp.setDesc(rs.getString("emp_desc"));
				emp.setDistrict(rs.getString("emp_native_district"));
				emp.setEduction(rs.getString("emp_xl"));
				emp.setEmail(rs.getString("emp_email"));
				emp.setEnglish(rs.getString("emp_eng_level"));
				emp.setGender(rs.getByte("gender"));
				emp.setOffertime(rs.getDate("emp_workdate"));
				emp.setPhone(rs.getString("emp_cellphone"));
				emp.setPhotoAdd(rs.getString("emp_photo"));
				emp.setPosition(rs.getString("emp_position"));
				emp.setProvince(rs.getString("emp_native_province"));
				emp.setSchool(rs.getString("emp_school"));
				emps.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.close();
		}
		return emps;
	}

	public ArrayList<Employee> getElementBySex(byte gender) {
		ArrayList<Employee> emps = new ArrayList<Employee>();
		Connection conn = db.getConnect();
		String sqlStr = "select * from employee where emp_gender=?";
		try {
			ps = conn.prepareStatement(sqlStr);
			ps.setByte(1, gender);
			rs = ps.executeQuery();
			while(rs.next()){
				Employee emp = new Employee();
				emp.setId(rs.getString("emp_id"));
				emp.setName(rs.getString("emp_name"));
				emp.setAddress(rs.getString("emp_address"));
				emp.setBirthday(rs.getDate("emp_birthday"));
				emp.setChinese(rs.getString("emp_mandarin_level"));
				emp.setCounty(rs.getString("emp_native_county"));
				emp.setDepid(rs.getInt("dept_id"));
				emp.setDesc(rs.getString("emp_desc"));
				emp.setDistrict(rs.getString("emp_native_district"));
				emp.setEduction(rs.getString("emp_xl"));
				emp.setEmail(rs.getString("emp_email"));
				emp.setEnglish(rs.getString("emp_eng_level"));
				emp.setGender(rs.getByte("gender"));
				emp.setOffertime(rs.getDate("emp_workdate"));
				emp.setPhone(rs.getString("emp_cellphone"));
				emp.setPhotoAdd(rs.getString("emp_photo"));
				emp.setPosition(rs.getString("emp_position"));
				emp.setProvince(rs.getString("emp_native_province"));
				emp.setSchool(rs.getString("emp_school"));
				emps.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.close();
		}
		return emps;
	}

	public Employee getElementById(String id) {
		ArrayList<Employee> emps = new ArrayList<Employee>();
		Employee emp = new Employee();
		Connection conn = db.getConnect();
		String sqlStr = "select * from employee where emp_id=?";
		try {
			ps = conn.prepareStatement(sqlStr);
			ps.setString(1, id);
			rs = ps.executeQuery();
				emp.setId(rs.getString("emp_id"));
				emp.setName(rs.getString("emp_name"));
				emp.setAddress(rs.getString("emp_address"));
				emp.setBirthday(rs.getDate("emp_birthday"));
				emp.setChinese(rs.getString("emp_mandarin_level"));
				emp.setCounty(rs.getString("emp_native_county"));
				emp.setDepid(rs.getInt("dept_id"));
				emp.setDesc(rs.getString("emp_desc"));
				emp.setDistrict(rs.getString("emp_native_district"));
				emp.setEduction(rs.getString("emp_xl"));
				emp.setEmail(rs.getString("emp_email"));
				emp.setEnglish(rs.getString("emp_eng_level"));
				emp.setGender(rs.getByte("gender"));
				emp.setOffertime(rs.getDate("emp_workdate"));
				emp.setPhone(rs.getString("emp_cellphone"));
				emp.setPhotoAdd(rs.getString("emp_photo"));
				emp.setPosition(rs.getString("emp_position"));
				emp.setProvince(rs.getString("emp_native_province"));
				emp.setSchool(rs.getString("emp_school"));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.close();
		}
		return emp;
	}

	public ArrayList<Employee> getElementByName(String name) {
		return null;
	}

	public ArrayList<Employee> getElements() {
		return null;
	}


	public ArrayList<Employee> getElementsByUser(String sqlStr) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean addElement(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delElements(int[] ids) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateElement(Employee emp) {
		// TODO Auto-generated method stub
		return false;
	}

}
