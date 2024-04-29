package com.example.Student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Student.domain.Katedra;
import com.example.Student.domain.Polaganje;
import com.example.Student.domain.Predmet;
import com.example.Student.domain.Student;

@Repository
public interface PolaganjeRepository extends JpaRepository<Polaganje, Long>{

	List<Polaganje> findByStudentId(Long id);
	List<Polaganje>  findByStudentIdAndOcenaGreaterThan(Long id, int ocena);
}
