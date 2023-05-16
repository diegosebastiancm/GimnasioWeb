package com.app.web.entities;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Entrenador")
public class Entrenador extends Persona{

	public Entrenador(Integer id, String documento, String nombre, String apellido, String telefono, String email,
			char genero, String password) {
		super(id, documento, nombre, apellido, telefono, email, genero, password);
	}
	
	@Column(name = "direccion", nullable=false)
	private String direccion;
	
}
