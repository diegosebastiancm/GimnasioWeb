package com.app.web.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Cliente")
public class Cliente extends Persona {
	
	public Cliente(Integer id, String documento, String nombre, String apellido, String telefono, String email,
			char genero, String password) {
		super(id, documento, nombre, apellido, telefono, email, genero, password);
	}

	@Column(name = "peso", nullable = false)
	private BigDecimal peso;
	
	@Column(name = "altura", nullable = false)
	private BigDecimal altura;
	
	@Column(name = "imc", nullable = false)
	private BigDecimal imc;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_inscripcion", nullable = false)
	private Date fecha_inscripcion;

	@Column(name = "plan", nullable = false)
	private String plan;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_nacimiento", nullable = false)
	private Date fecha_nacimiento;
	
	@Column(name = "edad", nullable = false)
	private Integer edad;
	
	@ManyToOne
	@JoinColumn(name="id_rutina_semanal")
	private List<Rutina_Semanal> rutina_semanal;
	
}
