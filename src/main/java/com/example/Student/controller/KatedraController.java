package com.example.Student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Student.domain.Katedra;
import com.example.Student.service.KatedraService;

@RestController
@RequestMapping("/api/katedra")
public class KatedraController {

	@Autowired
	private KatedraService katedraService;

	
	@GetMapping
	public List<Katedra> vratiSveKatedre(){
		return katedraService.vratiSveKatedre();
	}
	
	@PostMapping
	public Katedra dodajKatedru(@RequestBody Katedra katedra) {
		return katedraService.dodajKatedru(katedra);
	}


	@GetMapping("/{id}")
	public int vratiEspbZaKatedru(@PathVariable Long id) {
		return katedraService.vratiEspbZaKatedru(id);
	}
}
