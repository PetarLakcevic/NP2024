package com.example.Student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Student.domain.Predmet;
import com.example.Student.service.PredmetService;

@RestController
@RequestMapping("/api/predmet")
public class PredmetController {

	
	@Autowired
	private PredmetService predmetService;
	
	
	@PostMapping("/katedra/{id}")
	public Predmet kreirajPredmet(@PathVariable Long id, @RequestBody Predmet predmet) {
		return predmetService.kreirajPredmet(predmet, id);
	}
	
	@PatchMapping("/{id}/promeni-espb")
	public Predmet promeniEspb(@PathVariable Long id, @RequestBody Integer espb) {
		return predmetService.promeniEspb(id, espb);
	}
	
	@DeleteMapping("/{id}")
	public void obrisiPredmet(@PathVariable Long id) {
		predmetService.obrisiPredmet(id);
	}
}
