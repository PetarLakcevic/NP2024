package com.example.Student.seed;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.Student.domain.Katedra;
import com.example.Student.domain.Predmet;
import com.example.Student.domain.Smer;
import com.example.Student.domain.Student;
import com.example.Student.repository.KatedraRepository;
import com.example.Student.repository.PredmetRepository;
import com.example.Student.repository.StudentRepository;

@Component
public class SeedConfiguration implements CommandLineRunner{
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private KatedraRepository katedraRepository;

	@Autowired
	private PredmetRepository predmetRepository;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Popunjavanje baze...");
		seedStudents();
		seedKatedrasAndPredmets();
		System.out.println("Popunjavanje baze zavrseno...");
	}

	private void seedStudents() {
		System.out.println("Unos studenata...");
		List<Student> studenti = new ArrayList<>();
		
		Student student1 = Student.builder()
				.ime("Petar Petrovic")
				.rbr(5)
				.godinaUpisa(2020)
				.smer(Smer.IS)
				.build();
		Student student2 = Student.builder()
				.ime("Ivana Ivanovic")
				.rbr(3)
				.godinaUpisa(2020)
				.smer(Smer.IS)
				.build();
		
		studenti.add(student1);
		studenti.add(student2);
		
		studentRepository.saveAll(studenti);
	}
	
	private void seedKatedrasAndPredmets() {
		System.out.println("Unos katedri...");
		Katedra k1 = Katedra.builder()
				.naziv("Katedra za matematiku")
				.build();
		
		Katedra k2 = Katedra.builder()
				.naziv("Katedra za Informacione Sisteme")
				.build();
		
		System.out.println("-------");
		System.out.println(k2);
		
		Katedra saved1 = katedraRepository.save(k1);
		Katedra saved2 = katedraRepository.save(k2);
		
		
		System.out.println("-------");
		System.out.println(saved2);
		
		System.out.println("Unos predmeta...");
		Predmet mat1 = Predmet.builder()
				.naziv("Matematika 1")
				.espb(6)
				.semestar(1)
				.katedra(saved1)
				.build();
		
		Predmet dms = Predmet.builder()
				.naziv("Diskretne matematicke strukture")
				.espb(5)
				.semestar(3)
				.katedra(saved1)
				.build();
		
		Predmet baze = Predmet.builder()
				.naziv("Baze podataka")
				.espb(6)
				.semestar(1)
				.katedra(saved2)
				.build();
		
		predmetRepository.save(dms);
		predmetRepository.save(mat1);
		predmetRepository.save(baze);
	}
	
}
