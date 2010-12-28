package com.eim.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.eim.beans.Department;
import com.eim.dao.DepartmentDAO;
import com.eim.db.DataBase;

/**
 * 
 * @author element
 *departmentDAO的实现类
 */
public class DepartmentDAOImpl implements DepartmentDAO{

	private DataBase db;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	public DepartmentDAOImpl() {
		db = new DataBase();
	}
	
	/**
	 * 查询某个部门的上级部门
	 */
	public Department getSuperDepartment(String id) {
		Connection conn = db.getConnect();
		Department dept = new Department();	
		String sqlStr = "select * from department where id=(select super_dept_id from department where id=?)";
		try {
			ps = conn.prepareStatement(sqlStr);
			ps.setString(0, id);
			rs = ps.executeQuery();
			dept.setId(rs.getInt("dept_id"));
			dept.setName(rs.getString("dept_name"));
			dept.setSuperid(rs.getInt("super_dept_id"));
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
		return dept;
	}

	/**
	 * 按部门号查询部门信息
	 */
	public Department getElementById(String id) {
		Connection conn = db.getConnect();
		Department dept = new Department();	
		String sqlStr = "select * from department where dept_id=?";
		try {
			ps = conn.prepareStatement(sqlStr);
			ps.setString(0, id);
			rs = ps.executeQuery();
			dept.setId(rs.getInt("dept_id"));
			dept.setName(rs.getString("dept_name"));
			dept.setSuperid(rs.getInt("super_dept_id"));
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
		return dept;
	}

	/**
	 * 按名字查询部门
	 */
	public ArrayList<Department> getElementByName(String name) {
		ArrayList<Department> depts = new ArrayList<Department>();
		Connection conn = db.getConnect();
		String sqlStr = "select * from department where dept_name=?";
		try {
			ps = conn.prepareStatement(sqlStr);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()){
				Department dept = new Department();	
				dept.setId(rs.getInt("dept_id"));
				dept.setName(rs.getString("dept_name"));
				dept.setSuperid(rs.getInt("super_dept_id"));
				depts.add(dept);
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
		return depts;
	}

	/**
	 * 查询所有部门信息
	 */
	public ArrayList<Department> getElements() {
		ArrayList<Department> depts = new ArrayList<Department>();
		Connection conn = db.getConnect();
		String sqlStr = "select * from department";
		try {
			ps = conn.prepareStatement(sqlStr);
			rs = ps.executeQuery();
			while(rs.next()){
				Department dept = new Department();	
				dept.setId(rs.getInt("dept_id"));
				dept.setName(rs.getString("dept_name"));
				dept.setSuperid(rs.getInt("super_dept_id"));
				depts.add(dept);
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
		return depts;
	}

	/**
	 * 删除多个部门
	 */
	public boolean delElements(String[] ids){
		int[] nums =null;
		Connection conn = db.getConnect();
		String sqlStr = "delete * from department where dept_id=?";
		try {
			ps = conn.prepareStatement(sqlStr);
			for (String id : ids) {
				int did = Integer.parseInt(id);
				ps.setInt(1, did);
			}
			nums = ps.executeBatch();
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
		if(nums.length==ids.length){
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * 用户自定义查询
	 */
	public ArrayList<Department> getElementsByUser(String sqlStr) {
		ArrayList<Department> depts = new ArrayList<Department>();
		Connection conn = db.getConnect();
		try {
			ps = conn.prepareStatement(sqlStr);
			rs = ps.executeQuery();
			while(rs.next()){
				Department dept = new Department();	
				dept.setId(rs.getInt("dept_id"));
				dept.setName(rs.getString("dept_name"));
				dept.setSuperid(rs.getInt("super_dept_id"));
				depts.add(dept);
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
		return depts;
	}

	/**
	 * 添加一个部门
	 */
	public boolean addElement(Department dept) {
		Connection conn = db.getConnect();
		String sqlStr = "insert into department values(?,?,?)";
		int num = 0;
		try {
			ps = conn.prepareStatement(sqlStr);
			ps.setString(2, dept.getName());
			ps.setInt(3, dept.getSuperid());
			num = ps.executeUpdate();
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
		if(num>0){
			return true;
		}
		else{
			return false;
		}
	}

	/**
	 * 删除一个部门
	 */
	public boolean updateElement(Department dept) {
		Connection conn = db.getConnect();
		String sqlStr = "update department set dept_name=?,super_dept_id=? where dept_id=?";
		int num = 0;
		try {
			ps = conn.prepareStatement(sqlStr);
			ps.setInt(1, dept.getId());
			ps.setString(2, dept.getName());
			ps.setInt(3, dept.getSuperid());
			num = ps.executeUpdate();
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
		if(num>0){
			return true;
		}
		else{
			return false;
		}
	}
	

}
