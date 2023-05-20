package com.app.web.entities;

import java.math.BigDecimal;
import java.sql.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
@Table(name = "Ejercicio_Realizado" , schema = "caso73")
public class Ejercicio_Realizado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_ejercicio_realizado", columnDefinition = "SERIAL")
	private Integer id_ejercicio_realizado;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "fecha", nullable = false)
	private Date fecha;

	@Column(name="peso_levantado", nullable=false)
	private BigDecimal peso_levantado;
	
	@Column(name="repeticiones_realizadas", nullable=false)
	private BigDecimal repeticiones_realizadas;
	
	@Column(name="series_realizadas", nullable=false)
	private BigDecimal series_realizadas;
	
	@ManyToOne
	@JoinColumn(name="persona_id")
	private Cliente cliente;
	
	@OneToOne
	@JoinColumn(name="actividad_id")
	private Actividad_Fisica ejericio;
	
}
