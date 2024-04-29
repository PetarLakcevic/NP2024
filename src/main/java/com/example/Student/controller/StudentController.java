package com.example.Student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Student.domain.Student;
import com.example.Student.dto.StudentDTO;
import com.example.Student.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping
	public Student dodajStudenta(@RequestBody Student student) {
		return studentService.dodajStudenta(student);
	}
	
	@GetMapping
	public List<StudentDTO> vratiSve(){
		return studentService.vratiSve();
	}
	
}
