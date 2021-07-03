package com.te.empwebapp.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.te.empwebapp.beans.EmployeeInfoBean;
import com.te.empwebapp.beans.User_info;
import com.te.empwebapp.beans.mail_info;
import com.te.empwebapp.customexp.EmployeeException;

@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	@PersistenceUnit
	private EntityManagerFactory factory;

	@Override
	public User_info authenticate(String email, String pwd) {
		EntityManager manager = factory.createEntityManager();

		String s = "select uid from User_info where email =: email";
		Query query = manager.createQuery(s);
		query.setParameter("email", email);

		String uid = (String) query.getSingleResult();
		System.out.println(uid);

		User_info infoBean = manager.find(User_info.class, uid);

		if (infoBean != null) {
			if (infoBean.getPassword().equals(pwd)) {
				return infoBean;
			} else {
				throw new EmployeeException("Password is wrong");
			}
		} else {
			throw new EmployeeException("Invalid ID");
		}

	}

	@Override
	public EmployeeInfoBean getEmployeeData(int id) {

		EntityManager manager = factory.createEntityManager();
		EmployeeInfoBean infoBean = manager.find(EmployeeInfoBean.class, id);
		manager.close();
		return infoBean;
	}

	@Override
	public boolean deleteEmpData(String mid, String from_id) {
		boolean isDeleted = false;
		System.out.println(mid + " " + from_id);
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
//			mail_info infoBean = manager.find(mail_info.class, mid);
//			manager.remove(infoBean);
			String q = "delete from mail_info where mid=:mid and from_id=:from_id";
			Query query = manager.createQuery(q);
			query.setParameter("mid", mid);
			query.setParameter("from_id", from_id);
			int n = query.executeUpdate();
			if (n == 1) {
				isDeleted = true;
			}
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		return isDeleted;
	}

	@Override
	public boolean addEmployee(String mid, String email,String email1, String subject, String message, String status,
			String id1) {
		System.out.println(email1+" "+id1+" set the email and id-.>>>>>>>>>>>>>>>>>>>");
		boolean isInserted = false;
		mail_info mail = new mail_info();

		User_info userLoggedin = new User_info();
		// userLoggedin.setUid(userLoggedin.getUid());

		// get to id from destination email

		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		User_info userDestination = new User_info();
		userDestination.setEmail(email);

		String s = "select uid from User_info where email =: email";
		Query query = manager.createQuery(s);
		query.setParameter("email", userDestination.getEmail());

		String id = (String) query.getSingleResult();
		System.out.println(id);

		mail.setMid(mid);
		mail.setFrom_id(id1);
		mail.setFrom_email(email1);
		mail.setTo_email(email);
		mail.setTo_id(id);
		mail.setSubject(subject);
		mail.setMessage(message);
		mail.setStatus(status);

		transaction.begin();

		manager.persist(mail);
		System.out.println("success");
		isInserted = true;
		transaction.commit();

		return isInserted;
	}

//	@Override
//	public boolean addEmployee(String mid ,String email,String subject,String message,String status) {
//		boolean isInserted = false;
//		EntityManager manager = factory.createEntityManager();
//		EntityTransaction transaction = manager.getTransaction();
//		
//		User_info user = new User_info();
//		
//		
//		User_info userDestination = new User_info();
//		userDestination.setEmail(email);
//		String s = "select uid from User_info where email =: email";
//		Query query = manager.createQuery(s);
//		query.setParameter("email", userDestination.getEmail());
//		String reciverid = (String) query.getSingleResult();
//		System.out.println(reciverid);
//		
//		
//		mail_info mail = new mail_info();
//		mail.setFrom_id(user.getUid());
//		mail.setTo_id(reciverid);
//		mail.setSubject(subject);
//		mail.setMessage(message);
//		mail.setStatus(status);
//		
//		try {
//			transaction.begin();
//			manager.persist(mail);
//			transaction.commit();
//			isInserted = true;
//		} catch (Exception e) {
//			transaction.rollback();
//			e.printStackTrace();
//		}
//
//		return isInserted;
//	}

	@Override
	public boolean updateRecord(EmployeeInfoBean employeeInfoBean) {
		boolean isUpdated = false;

		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
			EmployeeInfoBean actualInfo = manager.find(EmployeeInfoBean.class, employeeInfoBean.getId());

			if (employeeInfoBean.getName() != null && employeeInfoBean.getName() != "") {
				actualInfo.setName(employeeInfoBean.getName());
			}

			if (employeeInfoBean.getDob() != null) {
				actualInfo.setDob(employeeInfoBean.getDob());
			}

			if (employeeInfoBean.getPassword() != null && employeeInfoBean.getPassword() != "") {
				actualInfo.setPassword(employeeInfoBean.getPassword());
			}

			transaction.commit();
			isUpdated = true;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
		}

		return isUpdated;
	}

	@Override
	public List<mail_info> getAllEmployees(String from_id) {

		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from mail_info where to_id=:to_id");
		query.setParameter("to_id", from_id);
		ArrayList<mail_info> mail = new ArrayList<mail_info>();
		try {
			mail = (ArrayList<mail_info>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			mail = null;
		}
		return mail;
	}

	@Override
	public List<mail_info> getAllSent(String from_id) {
		EntityManager manager = factory.createEntityManager();
		Query query = manager.createQuery("from mail_info where from_id=:from_id");
		query.setParameter("from_id", from_id);
		ArrayList<mail_info> mail = new ArrayList<mail_info>();
		try {
			mail = (ArrayList<mail_info>) query.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
			mail = null;
		}
		return mail;
	}

	@Override
	public boolean deleteEmpData1(String mid, String from_id) {
		boolean isDeleted = false;
		System.out.println(mid + " " + from_id);
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		try {
			transaction.begin();
//			mail_info infoBean = manager.find(mail_info.class, mid);
//			manager.remove(infoBean);
			String q = "delete from mail_info where mid=:mid and to_id=:from_id";
			Query query = manager.createQuery(q);
			query.setParameter("mid", mid);
			query.setParameter("from_id", from_id);
			int n = query.executeUpdate();
			if (n == 1) {
				isDeleted = true;
			}
			transaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
			transaction.rollback();
		}

		return isDeleted;
	}

	@Override
	public String getEmail(String uid) {

		EntityManager manager = factory.createEntityManager();

		User_info u = manager.find(User_info.class, uid);

		return u.getEmail();
	}

}
