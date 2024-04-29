package com.example.Student.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Student.domain.Polaganje;
import com.example.Student.service.PolaganjeService;

@RestController
@RequestMapping("/api/polaganje")
public class PolaganjeController {

	@Autowired
	private PolaganjeService polaganjeService;
	
	
	@PostMapping("/predmet/{pid}/student/{sid}")
	public Polaganje dodajPolaganje(@PathVariable Long pid, @PathVariable Long sid,
			@RequestBody Polaganje polaganje) {
		return polaganjeService.dodajPolaganje(pid, sid, polaganje);	
	}
	
	@GetMapping
	public List<Polaganje> vratiSvaPolaganja(){
		return polaganjeService.vratiSvaPolaganja();
	}
	
	@GetMapping("/prosek/student/{id}")
	public double vratiProsek(@PathVariable Long id) {
		return polaganjeService.vratiProsek(id);
	}
}
