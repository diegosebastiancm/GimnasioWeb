package com.app.web.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.web.entities.Persona;
@Repository
public interface PersonaRepository extends JpaRepository<Persona,Integer>{
		public Persona findByEmail(String email);
		public Optional<Persona> findById(Integer id);
}
