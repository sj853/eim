package com.eim.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.eim.beans.Employee;
import com.eim.dao.EmployeeDAO;
import com.eim.db.DataBase;

/**
 * 
 * @author element 员工信息dao实现类
 */
public class EmployeeDAOImpl implements EmployeeDAO {

	private int rowPerpage=1;
	public int getRowPerpage() {
		return rowPerpage;
	}

	public void setRowPerpage(int rowPerpage) {
		this.rowPerpage = rowPerpage;
	}

	private DataBase db;
	private ResultSet rs = null;
	private PreparedStatement ps = null;

	public EmployeeDAOImpl(int rowPerpage) {
		db = new DataBase();
		this.rowPerpage= rowPerpage;
	}

	/**
	 * 
	 * 在某个部门下查询员工姓名为X的员工
	 */
	public ArrayList<Employee> getElementByNameAtDept(int dept_id, String name) {
		ArrayList<Employee> emps = new ArrayList<Employee>();
		Connection conn = db.getConnect();
		String sqlStr = "select * from employee where emp_name=? and dept_id=?";
		try {
			ps = conn.prepareStatement(sqlStr);
			ps.setString(1, name);
			ps.setInt(2, dept_id);
			rs = ps.executeQuery();
			while (rs.next()) {
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
		} finally {
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

	/**
	 * 查询性别为男或女的所有员工
	 */
	public ArrayList<Employee> getElementBySex(byte gender,int currentPage) {
		ArrayList<Employee> emps = new ArrayList<Employee>();
		Connection conn = db.getConnect();
		String sqlStr = "select * from employee where emp_gender=? limit "+(currentPage-1)*rowPerpage+","+rowPerpage;
		try {
			ps = conn.prepareStatement(sqlStr);
			ps.setByte(1, gender);
			rs = ps.executeQuery();
			while (rs.next()) {
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
		} finally {
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

	
	public int getElementsSizeBySex(byte gender){
		int len =0;
		Connection conn = db.getConnect();
		String sqlStr = "select count(emp_id) from employee where emp_gender="+gender;
		try {
			ps = conn.prepareStatement(sqlStr);
			rs = ps.executeQuery();
			while(rs.next())len = rs.getInt(1);
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
		return len;
	}
	/**
	 * 查询工号为X的员工
	 */
	public ArrayList<Employee> getElementById(String id) {
		ArrayList<Employee> emps = new ArrayList<Employee>();
		Employee emp = new Employee();
		Connection conn = db.getConnect();
		String sqlStr = "select * from employee where emp_id=?";
		try {
			ps = conn.prepareStatement(sqlStr);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				
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
		} finally {
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

	/**
	 * 查询姓名为X的员工
	 */
	public ArrayList<Employee> getElementByName(String name) {
		ArrayList<Employee> emps = new ArrayList<Employee>();
		Employee emp = new Employee();
		Connection conn = db.getConnect();
		String sqlStr = "select * from employee where emp_name=?";
		try {
			ps = conn.prepareStatement(sqlStr);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while(rs.next()){
				
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
		} finally {
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

	/**
	 * 查询所有员工
	 */
	public ArrayList<Employee> getElements(int currentPage) {
		ArrayList<Employee> emps = new ArrayList<Employee>();
		Employee emp = new Employee();
		Connection conn = db.getConnect();
		String sqlStr = "select * from employee limit "+(currentPage-1)*rowPerpage+","+rowPerpage;
		try {
			ps = conn.prepareStatement(sqlStr);
			rs = ps.executeQuery();
			while(rs.next()){
				
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
		} finally {
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

	
	/**
	 * 添加一个员工
	 */
	public boolean addElement(Employee emp) {
		int num = 0;
		Connection conn = db.getConnect();
		String sqlStr = "insert into employee values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			ps = conn.prepareStatement(sqlStr);
			ps.setString(1, emp.getId());
			ps.setString(2, emp.getName());
			ps.setByte(3, emp.getGender());
			ps.setString(4, emp.getSchool());
			ps.setDate(5, emp.getBirthday());
			ps.setString(6, emp.getProvince());
			ps.setString(7, emp.getCounty());
			ps.setString(8, emp.getDistrict());
			ps.setString(9, emp.getPhotoAdd());
			ps.setString(10, emp.getEduction());
			ps.setString(11, emp.getEnglish());
			ps.setString(12, emp.getChinese());
			ps.setString(13, emp.getPosition());
			ps.setDate(14, emp.getOffertime());
			ps.setInt(15, emp.getDepid());
			ps.setString(16, emp.getEmail());
			ps.setString(17, emp.getPhone());
			ps.setString(18, emp.getAddress());
			ps.setString(19, emp.getDesc());
			num = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
	 * 删除多个员工
	 */
	public boolean delElements(String[] ids) {
		int[] nums =null;
		Connection conn = db.getConnect();
		String sqlStr = "delete * from employee where emp_id=?";
		try {
			ps = conn.prepareStatement(sqlStr);
			for (String id : ids) {
				ps.setString(1, id);
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
	 * 修改员工信息
	 */
	public boolean updateElement(Employee emp) {
		Connection conn = db.getConnect();
		String sqlStr = "update employee set emp_namne=?,emp_gender=?,emp_school=?,emp_birthday=?,emp_native_province=?," +
				"emp_native_county=?,emp_native_district=?,emp_photo=?,emp_xl=?," +
				"emp_eng_level=?,emp_mandarin_level=?,emp_position=?,emp_workdate=?," +
				"dept_id=?,emp_email=?,emp_cellphone=?,emp_address=?,emp_desc=? where emp_id=?";
		int num = 0;
		try {
			ps = conn.prepareStatement(sqlStr);
			ps.setString(19, emp.getId());
			ps.setString(1, emp.getName());
			ps.setByte(2, emp.getGender());
			ps.setString(3, emp.getSchool());
			ps.setDate(4, emp.getBirthday());
			ps.setString(5, emp.getProvince());
			ps.setString(6, emp.getCounty());
			ps.setString(7, emp.getDistrict());
			ps.setString(8, emp.getPhotoAdd());
			ps.setString(9, emp.getEduction());
			ps.setString(10, emp.getEnglish());
			ps.setString(11, emp.getChinese());
			ps.setString(12, emp.getPosition());
			ps.setDate(13, emp.getOffertime());
			ps.setInt(14, emp.getDepid());
			ps.setString(15, emp.getEmail());
			ps.setString(16, emp.getPhone());
			ps.setString(17, emp.getAddress());
			ps.setString(18, emp.getDesc());
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
	 * 用户自定义查询
	 */
	public ArrayList<Employee> getElementsByUser(String sqlStr) {
		ArrayList<Employee> emps = new ArrayList<Employee>();
		Employee emp = new Employee();
		Connection conn = db.getConnect();
		try {
			ps = conn.prepareStatement(sqlStr);
			rs = ps.executeQuery();
			while(rs.next()){
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
		} finally {
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

	public int getElementsSize() {
		int len=0;
		Connection conn = db.getConnect();
		String sqlStr = "select count(id) from employee";
		try {
			ps = conn.prepareStatement(sqlStr);
			rs = ps.executeQuery();
			while(rs.next())len = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			db.close();
		}
		return len;

	}

	public int getElementsSizeByUser(String sqlStr) {
		// TODO Auto-generated method stub
		return 0;
	}
}
