package com.example.Student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Student.domain.Katedra;
import com.example.Student.domain.Predmet;

@Repository
public interface PredmetRepository extends JpaRepository<Predmet, Long>{


}
