package com.example.Student.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Student.domain.Polaganje;
import com.example.Student.domain.Predmet;
import com.example.Student.domain.Student;
import com.example.Student.repository.PolaganjeRepository;
import com.example.Student.repository.PredmetRepository;
import com.example.Student.repository.StudentRepository;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class PolaganjeService {
	
	
	private static final Logger logger = LoggerFactory.getLogger(PolaganjeService.class);

	@Autowired
	private PolaganjeRepository polaganjeRepository;
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private PredmetRepository predmetRepository;

	public Polaganje dodajPolaganje(Long pid, Long sid, Polaganje polaganje) {
		
		logger.info("Dodajem novo polaganje: "+polaganje.toString()+" ovo je info poruka");
		logger.error("Dodajem novo polaganje: "+polaganje.toString()+" ovo je error poruka");
		
		
		if (polaganje.getOcena() > 10 || polaganje.getOcena() < 5) {
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

	public List<Polaganje> vratiSvaPolaganja() {
		return polaganjeRepository.findAll();
	}

	public double vratiProsek(Long id) {

		List<Polaganje> svaPolaganjaOvogStudneta = polaganjeRepository.findByStudentId(id);

		double sum = 0;
		for (Polaganje polaganje : svaPolaganjaOvogStudneta) {
			sum += polaganje.getOcena();
			System.out.println(polaganje.getStudent().getIme() + " ocena: " + polaganje.getOcena());
		}

		return sum / svaPolaganjaOvogStudneta.size();

	}

	public double vratiProsekBezPadanja(Long id) {

		List<Polaganje> svaPolaganjaOvogStudneta = polaganjeRepository.findByStudentIdAndOcenaGreaterThan(id, 5);

		double sum = 0;
		for (Polaganje polaganje : svaPolaganjaOvogStudneta) {
			sum += polaganje.getOcena();
			System.out.println(polaganje.getStudent().getIme() + " ocena: " + polaganje.getOcena());
		}

		return sum / svaPolaganjaOvogStudneta.size();

	}

	public void izvozSvihOcena(HttpServletResponse response) throws IOException {
		
		logger.info("Export polaganja...");

		/*
		 * Ime/god_upisa/rbr/katedra/prdmet/bodovi/ocena/datum
		 */

		List<Polaganje> svaPolaganja = polaganjeRepository.findAll();

		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();

		Row headerRow = sheet.createRow(0);

		String[] columns = { "Ime", "Godina_Upisa", "Redni broj", "Katedra", 
				"Predmet", "Bodovi", "Ocena", "Datum" };

		for (int i = 0; i < columns.length; i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(columns[i]);
		}

		int rowCounter = 1;
		for (Polaganje polaganje : svaPolaganja) {
			int cellCounter = 0;
			Row row = sheet.createRow(rowCounter++);
			
			row.createCell(cellCounter++).setCellValue(polaganje.getStudent().getIme());
			row.createCell(cellCounter++).setCellValue(polaganje.getStudent().getGodinaUpisa());
			row.createCell(cellCounter++).setCellValue(polaganje.getStudent().getRbr());
			row.createCell(cellCounter++).setCellValue(polaganje.getPredmet().getKatedra().getNaziv());
			row.createCell(cellCounter++).setCellValue(polaganje.getPredmet().getNaziv());
			row.createCell(cellCounter++).setCellValue(polaganje.getBodovi());
			row.createCell(cellCounter++).setCellValue(polaganje.getOcena());
			
			
			
			row.createCell(cellCounter++).setCellValue(polaganje.getDatumUnosaOcene().toString());
			
		}
		
		
		for (int i = 0; i < columns.length; i++) {
			sheet.autoSizeColumn(i);
		}
		
		response.setContentType("application/vnd.openxmlformats-officeducment.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=\"polaganja.xlsx\"");
		
		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();
		outputStream.close();
	}
}
