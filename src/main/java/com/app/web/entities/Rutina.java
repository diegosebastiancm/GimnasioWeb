package com.app.web.entities;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Rutina_Diaria" , schema = "caso73")
public class Rutina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rutina_diaria", columnDefinition = "SERIAL")
	private Integer id_rutina_diaria;
	
	@Column(name = "dia_semana", nullable=false)
	private String dia_semana;
	@ManyToOne
	@JoinColumn(name = "rutina_semanal_id")
	private Rutina_Semanal rutinaSemanal;

	@ManyToOne
	@JoinColumn(name = "objetivo_id")
	private Objetivo objetivo;
	
	@OneToMany(mappedBy="rutina")
	private List<Ejercicio> ejercicios;
}
