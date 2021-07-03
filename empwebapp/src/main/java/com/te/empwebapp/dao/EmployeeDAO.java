package com.te.empwebapp.dao;

import java.util.List;

import com.te.empwebapp.beans.EmployeeInfoBean;
import com.te.empwebapp.beans.User_info;
import com.te.empwebapp.beans.mail_info;

public interface EmployeeDAO {

	public User_info authenticate(String email, String pwd);

	public EmployeeInfoBean getEmployeeData(int id);

	public boolean deleteEmpData(String uid,String from_id);
	
	public boolean deleteEmpData1(String uid,String from_id);

	public boolean addEmployee(String mid ,String email,String email1,String subject,String message,String status,String id1);

	public boolean updateRecord(EmployeeInfoBean employeeInfoBean);

	public List<mail_info> getAllEmployees(String from_id);
	
	public List<mail_info> getAllSent(String from_id);
	
	public String getEmail(String uid);
	
}
