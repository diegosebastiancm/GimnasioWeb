package com.app.web.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Persona", schema = "caso73")
public class Persona {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "SERIAL")
	private Integer id;

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

	@OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Cliente cliente;

	@OneToOne(mappedBy = "persona", cascade = CascadeType.ALL)
	@PrimaryKeyJoinColumn
	private Entrenador entrenador;

}
