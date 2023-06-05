package com.app.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.web.entities.Ejercicio;
import com.app.web.entities.Rutina;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {

	
	List<Ejercicio> findByRutina(Rutina rutina);
}
