package com.abhi.act.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.act.model.Student;

public interface IStudentRepository extends JpaRepository<Student, Integer>{

	  Student  findByEmail(String email);
	
}
