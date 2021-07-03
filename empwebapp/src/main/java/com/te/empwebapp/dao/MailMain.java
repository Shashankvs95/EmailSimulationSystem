package com.te.empwebapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.te.empwebapp.beans.User_info;
import com.te.empwebapp.beans.mail_info;

public class MailMain {

	public static void main(String[] args) {

		mail_info mail = new mail_info();

		User_info userLoggedin = new User_info();
		userLoggedin.setUid("1");

		// get to id from destination email

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();

		User_info userDestination = new User_info();
		userDestination.setEmail("shashank@gmail.com");

		String s = "select uid from User_info where email =: email";
		Query query = manager.createQuery(s);
		query.setParameter("email", userDestination.getEmail());

		String id = (String) query.getSingleResult();
		System.out.println(id);

		mail.setMid("m1");
		mail.setFrom_email("sandeep@gmail.com");
		mail.setTo_email("shashank@gmail.com");
		mail.setFrom_id(userLoggedin.getUid());
		mail.setTo_id(id);
		mail.setSubject("holiday");
		mail.setMessage("i am going to ooty");
		mail.setStatus("1");

		transaction.begin();

		manager.persist(mail);

		transaction.commit();

	}

}
