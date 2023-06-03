package com.app.web.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Ejercicio" , schema = "caso73")
public class Ejercicio {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ejercicio", columnDefinition = "SERIAL")
	private Integer id_ejercicio;
	
	@OneToOne
	@JoinColumn(name="id_actividad")
	private Actividad_Fisica ejercicio;
	
	@Column(name="peso_ejercicio")
	private BigDecimal peso_ejercicio;
	
	@Column(name="repeticiones", nullable=false)
	private BigDecimal repeticiones;
	
	@Column(name="series", nullable=false)
	private BigDecimal series;
	
	@ManyToOne
	@JoinColumn(name="Rutina_id")
	private Rutina rutina;
}
