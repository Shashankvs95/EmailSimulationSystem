package com.te.empwebapp.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table
public class mail_info {

	@Id
	@Column
	private String mid;
	
	@Column
	private String from_id;
	
	@Column
	private String to_id;
	
	@Column
	private String subject;
	
	@Column
	private String message;
	
	@Column
	private String status;
	
	@Column
	private String from_email;
	
	@Column
	private String to_email;
	
}
