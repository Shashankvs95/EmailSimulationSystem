package com.te.empwebapp.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.te.empwebapp.beans.User_info;

public class UserMain {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("emsPeristenceUnit");
		EntityManager manager = emf.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		User_info user = new User_info();
		user.setUid("1".toLowerCase());
		user.setUsername("shashank".toLowerCase());
		user.setEmail("shashank@gmail.com".toLowerCase());
		user.setPassword("1234".toLowerCase());
		
		transaction.begin();
		
		manager.persist(user);
		System.out.println("user inserted successfully");
		
		transaction.commit();
	}
}
