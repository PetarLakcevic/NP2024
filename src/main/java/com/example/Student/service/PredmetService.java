package com.example.Student.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Student.domain.Katedra;
import com.example.Student.domain.Predmet;
import com.example.Student.repository.KatedraRepository;
import com.example.Student.repository.PredmetRepository;

@Service
public class PredmetService {

	
	@Autowired
	private PredmetRepository predmetRepository;
	
	@Autowired
	private KatedraRepository katedraRepository;
	
	
	public Predmet kreirajPredmet(Predmet predmet, Long katedraId) {
		
		Optional<Katedra> optionalKatedra = katedraRepository.findById(katedraId);
		if (!optionalKatedra.isPresent()) {
			throw new RuntimeException("Pogresan ID katedre");
		}
		
		
		predmet.setKatedra(optionalKatedra.get());
		Predmet sacuvan = predmetRepository.save(predmet);
		return sacuvan;
	}

	
	public Predmet promeniEspb(Long id, Integer espb) {
		Optional<Predmet> optionalPredmet = predmetRepository.findById(id);
		if(!optionalPredmet.isPresent()) {
			throw new RuntimeException("Predmet sa ID: "+id+" ne postoji.");
		}
		
		Predmet predmet = optionalPredmet.get();
		predmet.setEspb(espb);
		return predmetRepository.save(predmet);
	}
	
	
	public void obrisiPredmet(Long id) {
		Optional<Predmet> optionalPredmet = predmetRepository.findById(id);
		if(!optionalPredmet.isPresent()) {
			throw new RuntimeException("Predmet sa ID: "+id+" ne postoji.");
		}
		
		predmetRepository.deleteById(id);
	}
	
}
