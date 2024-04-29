package com.example.Student.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Student.domain.Katedra;
import com.example.Student.domain.Predmet;
import com.example.Student.repository.KatedraRepository;

@Service
public class KatedraService {

	@Autowired
	private KatedraRepository katedraRepository;

	public List<Katedra> vratiSveKatedre() {
		return katedraRepository.findAll();
	}

	public Katedra dodajKatedru(Katedra katedra) {
		// validacija
		if (katedra.getNaziv() == null || katedra.getNaziv().isEmpty()) {
			throw new RuntimeException("Morate uneti naziv");
		}

		Katedra sacuvana = katedraRepository.save(katedra);
		return sacuvana;
	}

	public int vratiEspbZaKatedru(Long id) {
		Optional<Katedra> optionalKatedra = katedraRepository.findById(id);

		if (!optionalKatedra.isPresent()) {
			throw new RuntimeException("Katedra sa ID: " + id + " ne postoji");
		}

		Katedra katedra = optionalKatedra.get();

		int sum = 0;
		for (Predmet predmet : katedra.getPredmetSet()) {
			sum += predmet.getEspb();
		}

		return sum;
	}

}
