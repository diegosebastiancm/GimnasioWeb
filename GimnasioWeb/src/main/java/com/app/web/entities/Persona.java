package com.app.web.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Persona")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_persona", columnDefinition = "SERIAL")
	private Integer id_persona;
	
	@Column(name = "documento", nullable = false)
	private String documento;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "apellido", nullable = false)
	private String apellido;
	
	@Column(name = "telefono", nullable = false)
	private String telefono;
	
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "genero", nullable = false)
	private char genero;
	
	@Column(name = "password", nullable = false)
	private String password;
	
}
