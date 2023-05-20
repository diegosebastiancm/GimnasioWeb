package com.app.web.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "Actividad_Fisica" , schema = "caso73")
public class Actividad_Fisica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_actividad", columnDefinition = "SERIAL")
	private Integer id_actividad;

	@Column(name = "nombre_actividad", nullable=false)
	private String nombre_actividad;

	@Column(name = "link", nullable=false)
	private String link;
}
