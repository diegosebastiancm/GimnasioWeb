package com.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entities.Objetivo;
import com.app.web.repository.ObjetivoRepository;

@Controller
//Objetivo solo sera modificado por entrenador
public class ObjetivoController {
	@Autowired
	private ObjetivoRepository objetivoRepository;
    
    @GetMapping({"/objetivos","/objetivos/listar"})
	public String listar(Model model) {
		List<Objetivo> objetivos = objetivoRepository.findAll();
		model.addAttribute("objetivos",objetivos);
		return "index_objetivos";
	}
	
	@GetMapping("/objetivos/registrar")
	public String mostrarFormulario(Model modelo) {
		Objetivo objetivo = new Objetivo();
		modelo.addAttribute("objetivo", objetivo);
		return "formulario_objetivo";
	}

	@GetMapping("/objetivos/ver/{id}")
	public String mostrarVerObjetivo(@PathVariable Integer id, Model modelo) {
		Objetivo objetivo = objetivoRepository.findById(id).get();
		modelo.addAttribute("objetivo",objetivo);
		return "ver_objetivo";
	}

	@PostMapping({ "/objetivos", "/objetivos/listar" })
	public String guardarObjetivo(Objetivo objetivo) {
		objetivoRepository.save(objetivo);
		return "redirect:/objetivos";
	}

	@GetMapping("/objetivos/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model modelo) {
		Objetivo objetivo =  objetivoRepository.findById(id).get();
		modelo.addAttribute("objetivo",objetivo);
		return "formulario_objetivo";
	}

	@GetMapping("/objetivos/{id}")
	public String eliminarObjetivo(@PathVariable Integer id, Model modelo) {
		objetivoRepository.deleteById(id);
		return "redirect:/objetivos";
	}
	@GetMapping("/verObjetivo")
	public String verObjetivo() {
		return "redirect:/objetivos";
	}
}
