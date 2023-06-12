package com.app.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.web.entities.Cliente;
import com.app.web.entities.Entrenador;
import com.app.web.entities.Persona;
import com.app.web.repository.ClienteRepository;
import com.app.web.repository.EntrenadorRepository;
import com.app.web.repository.PersonaRepository;
@Controller
@RequestMapping("/admin")
public class AdministradorController {
	@Autowired
	PersonaRepository personaRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EntrenadorRepository entrenadorRepository;

	 @GetMapping()
	 public String listar(Model model) {
		    List<Integer> clienteIds = clienteRepository.findAll().stream()
		            .map(Cliente::getPersona_id)
		            .collect(Collectors.toList());
		    List<Persona> clientes = personaRepository.findAllById(clienteIds);
		    model.addAttribute("clientes", clientes);
		    List<Integer> entrenadorIds = entrenadorRepository.findAll().stream()
		            .map(Entrenador::getPersona_id)
		            .collect(Collectors.toList());
		    List<Persona> entrenadores = personaRepository.findAllById(entrenadorIds);
		    model.addAttribute("entrenadores", entrenadores);
		    
		    return "index_admin";
		}
}
