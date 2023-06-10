package com.app.web.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.web.entities.Cliente;


public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	public Cliente findByPersona_id(int persona_id);
}
