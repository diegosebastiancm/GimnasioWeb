package com.app.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.app.web.entities.Persona;
import com.app.web.service.PersonaServicio;

@Controller
@RequestMapping("/registro")
public class RegistroPersonaController {

	private PersonaServicio personaServicio;

	public RegistroPersonaController(PersonaServicio personaServicio) {
		super();
		this.personaServicio = personaServicio;
	}
	
	@ModelAttribute("persona")
	public Persona retornarNuevaPersona() {
		return new Persona();
	}

	@GetMapping()
	public String mostrarFormularioDeRegistro() {
		return "registro";
	}
	
	@PostMapping
	public String registrarCuentaDePersona(@ModelAttribute("persona") Persona persona) {
		personaServicio.guardar(persona);
		return "redirect:/registro?exito";
	}
}
