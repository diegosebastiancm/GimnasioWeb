package com.app.web.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.web.entities.Rutina;
import com.app.web.entities.Rutina_Semanal;

public interface RutinaRepository extends JpaRepository<Rutina, Integer>{
	
	List<Rutina> findByRutinaSemanal(Rutina_Semanal rutinaSemanal);
}
