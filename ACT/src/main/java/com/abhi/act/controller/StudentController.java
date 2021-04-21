package com.abhi.act.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.act.exception.EmailAlreadyExists;
import com.abhi.act.model.Student;
import com.abhi.act.repository.IStudentRepository;
import com.abhi.act.service.StudentService;

@CrossOrigin(value = "http://localhost:4200")
@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
	private IStudentRepository studentRepo;
	// POST

	@PostMapping(path = "/create")
	public ResponseEntity<?> postStudent(@RequestBody Student student) {

		try {
			Student email=studentRepo.findByEmail(student.getEmail());
			if(email!=null) {
				throw new  EmailAlreadyExists("Email Already Exists");
			}
			else {
				Student theStudent = studentService.createStudent(student);

				return new ResponseEntity<>(theStudent, HttpStatus.CREATED);
			}
		
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// GET ALL Students
	@GetMapping(path = "/getAllStudents")
	public ResponseEntity<?> retrieveAllStudent() {

		try {
			List<Student> theStudent = studentService.getAllStudents();

			return new ResponseEntity<>(theStudent, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// GET STUDENT BY ID
	@GetMapping(path = "/{studentId}")
	public ResponseEntity<?> retrieveStudent(@PathVariable int studentId) {

		try {
			Optional<Student> theStudent = studentService.findStudent(studentId);

			return new ResponseEntity<>(theStudent, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// GET STUDENT BY ID
	@DeleteMapping(path = "/{studentId}")
	public ResponseEntity<?> removeStudent(@PathVariable int studentId) {

		try {
			ResponseEntity<?> theStudent = studentService.deleteStudent(studentId);

			return new ResponseEntity<>(theStudent, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getLocalizedMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
