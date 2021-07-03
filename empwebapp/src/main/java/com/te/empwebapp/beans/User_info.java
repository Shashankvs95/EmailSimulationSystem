package com.te.empwebapp.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class User_info implements Serializable {

	@Id
	@Column
	private String uid;
	
	@Column
	private String username;
	
	@Column
	private String email;
	
	@Column
	private String password;
	
	
	
}
