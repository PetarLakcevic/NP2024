package com.example.Student.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Student.domain.Polaganje;
import com.example.Student.domain.Predmet;
import com.example.Student.domain.Student;
import com.example.Student.repository.PolaganjeRepository;
import com.example.Student.repository.PredmetRepository;
import com.example.Student.repository.StudentRepository;

@Service
public class PolaganjeService {

	@Autowired
	private PolaganjeRepository polaganjeRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private PredmetRepository predmetRepository;
	
	
	public Polaganje dodajPolaganje(Long pid, Long sid, Polaganje polaganje) {
		if (polaganje.getOcena()>10 || polaganje.getOcena()<5) {
			throw new RuntimeException("Lose uneta ocena");
		}
		
		Optional<Predmet> optionalPredmet = predmetRepository.findById(pid);	
		if (!optionalPredmet.isPresent()) {
			throw new RuntimeException("Nije pronadjen predmet");
		}
				
		Optional<Student> optionalStudent = studentRepository.findById(sid);
		if (!optionalStudent.isPresent()) {
			throw new RuntimeException("Nije pronadjen student");
		}
	
		polaganje.setDatumUnosaOcene(LocalDateTime.now());
		polaganje.setPredmet(optionalPredmet.get());
		polaganje.setStudent(optionalStudent.get());
		return polaganjeRepository.save(polaganje);
	}
	
	public List<Polaganje> vratiSvaPolaganja(){
		return polaganjeRepository.findAll();
	}

	public double vratiProsek(Long id) {
		
		List<Polaganje> svaPolaganjaOvogStudneta = polaganjeRepository.findByStudentId(id);
		
		double sum = 0;
		for (Polaganje polaganje : svaPolaganjaOvogStudneta) {
			sum+=polaganje.getOcena();
			System.out.println(polaganje.getStudent().getIme() + " ocena: "+polaganje.getOcena());
		}
		
		return sum/svaPolaganjaOvogStudneta.size();
		
	}
	
	public double vratiProsekBezPadanja(Long id) {
		
		List<Polaganje> svaPolaganjaOvogStudneta = polaganjeRepository.findByStudentIdAndOcenaGreaterThan(id,5);
		
		double sum = 0;
		for (Polaganje polaganje : svaPolaganjaOvogStudneta) {
			sum+=polaganje.getOcena();
			System.out.println(polaganje.getStudent().getIme() + " ocena: "+polaganje.getOcena());
		}
		
		return sum/svaPolaganjaOvogStudneta.size();
		
	}
}
