package com.app.web.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "Cliente", schema = "caso73")
public class Cliente {
	@Id
	@Column(name = "persona_id")
	private int persona_id;
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

	@OneToMany(mappedBy = "cliente")
	private List<Rutina_Semanal> rutina_semanal;

	@OneToMany(mappedBy = "cliente")
	private List<Ejercicio_Realizado> ejercicioRealizado;

	@OneToOne
	@MapsId
	@JoinColumn(name = "persona_id")
	private Persona persona;

}
