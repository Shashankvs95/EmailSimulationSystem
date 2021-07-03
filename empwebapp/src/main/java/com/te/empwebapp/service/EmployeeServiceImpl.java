package com.te.empwebapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.te.empwebapp.beans.EmployeeInfoBean;
import com.te.empwebapp.beans.User_info;
import com.te.empwebapp.beans.mail_info;
import com.te.empwebapp.dao.EmployeeDAO;

@Service("serviceone")
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDAO dao;

	@Override
	public User_info authenticate(String email, String pwd) {
		System.out.println("Service layer one");
		
			return dao.authenticate(email, pwd);
		

	}

	@Override
	public EmployeeInfoBean getEmployeeData(int id) {
		if (id <= 0) {
			return null;
		}
		return dao.getEmployeeData(id);
	}

	@Override
	public boolean deleteEmpData(String  mid,String from_id) {

		return dao.deleteEmpData(mid,from_id);
	}
	
	@Override
	public boolean deleteEmpData1(String  mid,String from_id) {

		return dao.deleteEmpData1(mid,from_id);
	}

	@Override
	public boolean addEmployee(String mid ,String email,String email1,String subject,String message,String status,String id1) {

		return dao.addEmployee(mid,email,email1,subject,message,status,id1);
	}

	@Override
	public boolean updateRecord(EmployeeInfoBean employeeInfoBean) {
		// TODO Auto-generated method stub
		return dao.updateRecord(employeeInfoBean);
	}

	@Override
	public List<mail_info> getAllEmployees(String from_id) {
		// TODO Auto-generated method stub
		return dao.getAllEmployees(from_id);
	}

	@Override
	public List<mail_info> getAllSent(String from_id) {
		return dao.getAllSent(from_id);
	}

	@Override
	public String getEmail(String uid) {
		// TODO Auto-generated method stub
		return dao.getEmail(uid);
	}

	

}
