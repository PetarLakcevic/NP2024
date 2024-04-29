package com.example.Student.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Predmet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String naziv;
	private int espb;
	private int semestar;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "katedra_id")
	private Katedra katedra;
	
}
