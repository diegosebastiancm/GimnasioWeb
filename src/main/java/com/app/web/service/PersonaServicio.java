package com.app.web.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.app.web.entities.Persona;

public interface PersonaServicio extends UserDetailsService{

	public Persona guardar(Persona persona);
	
	public List<Persona> listarPersonas();
	
}
