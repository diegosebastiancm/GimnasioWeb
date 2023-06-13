package com.app.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.web.entities.Cliente;
import com.app.web.entities.Ejercicio_Realizado;

public interface Ejercicio_RealizadoRepository extends JpaRepository<Ejercicio_Realizado, Integer>{

	List<Ejercicio_Realizado> findByCliente(Cliente cliente);
}
