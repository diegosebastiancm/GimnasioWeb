package com.app.web.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import java.sql.Date;
import java.util.List;

@Entity
@Data
@Table(name = "Rutina_Semanal" , schema = "caso73")
public class Rutina_Semanal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_rutina_semanal", columnDefinition = "SERIAL")
	private Integer id_rutina_semanal;
	
	@Column(name = "fecha_de_inicio", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha_de_inicio;
	
	@Column(name = "fecha_de_fin", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date fecha_de_fin;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@OneToMany(mappedBy = "rutinaSemanal")
	private List<Rutina> rutinas;
	
}
