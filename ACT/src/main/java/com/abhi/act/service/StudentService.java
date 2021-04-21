package com.abhi.act.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.abhi.act.model.Student;
import com.abhi.act.repository.IStudentRepository;

@Service
public class StudentService {

	@Autowired
	private IStudentRepository studentRepository;

	// Create STUDENT
	public Student createStudent(Student student) {
          
		student.setCreatedDate(new Date());
		return studentRepository.save(student);
	}

	// GET ALL STUDENTS

	public List<Student> getAllStudents() {

		return studentRepository.findAll();
	}

	// GET STUDENT BY ID

	public Optional<Student> findStudent(int studentId) {

		return studentRepository.findById(studentId);
	}

	// DELETE ALL
//	
//	public Optional<Student> deleteAllStudents() {
//		
//		return studentRepository.deleteAll();
//	}

	// DELETE ALL BY ID

	public ResponseEntity<?> deleteStudent(int studentId) {
		String message = "";
		try {
			message = "Deleted Sucessfully:" + studentId;
			studentRepository.deleteById(studentId);
			return new ResponseEntity<>(message, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
