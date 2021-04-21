package com.abhi.act.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "student_details")
public class Student {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int studentId;

	@Column(name = "STUDENT_NAME",length = 255,unique =false)
	private String studentName;

	private String password;

	private String email;

	private long mobileNo;

	private String address;

	private String gender;

	private Date createdDate;

	private Date updatedDate;

}
