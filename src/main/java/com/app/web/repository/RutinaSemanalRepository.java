package com.app.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.web.entities.Cliente;
import com.app.web.entities.Rutina_Semanal;

public interface RutinaSemanalRepository extends JpaRepository<Rutina_Semanal, Integer>{

	List<Rutina_Semanal> findByCliente(Cliente cliente);
}
