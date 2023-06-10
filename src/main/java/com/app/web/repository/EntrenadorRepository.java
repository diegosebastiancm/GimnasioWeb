package com.app.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.web.entities.Entrenador;

public interface EntrenadorRepository extends JpaRepository<Entrenador, Integer>{
	public Entrenador findByPersona_id(int persona_id);
}
