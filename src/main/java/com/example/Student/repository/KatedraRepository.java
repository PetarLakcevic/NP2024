package com.example.Student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Student.domain.Katedra;

@Repository
public interface KatedraRepository extends JpaRepository<Katedra, Long>{

	
	//custom metoda za komunikaciju sa bazom
	
	//spring data
	
	//custom query
}
