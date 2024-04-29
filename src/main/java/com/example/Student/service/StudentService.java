package com.example.Student.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Student.domain.Student;
import com.example.Student.dto.StudentDTO;
import com.example.Student.repository.StudentRepository;

@Service
public class StudentService {

	
	@Autowired
	private StudentRepository studentRepository;
	
	
	public Student dodajStudenta(Student student) {
		return studentRepository.save(student);
	}
	
	public List<StudentDTO> vratiSve(){
		List<Student> studenti = studentRepository.findAll();
		
		List<StudentDTO> studentDTOs = new ArrayList<>();
		
		for (Student student : studenti) {
			StudentDTO studentDTO = new StudentDTO();
			studentDTO.setIme(student.getIme());
			studentDTO.setIndeks(student.getRbr()+"/"+student.getGodinaUpisa());
			
			studentDTOs.add(studentDTO);
		}
		
		return studentDTOs;
		
	}
}
