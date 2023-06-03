package com.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.app.web.entities.Rutina;
import com.app.web.entities.Rutina_Semanal;
import com.app.web.repository.RutinaRepository;
import com.app.web.repository.RutinaSemanalRepository;

@Controller
public class RutinaSemanalController {

	@Autowired
	RutinaSemanalRepository repositorio;
	
	@Autowired
	RutinaRepository repositorioRutina;
	
	@GetMapping("/rutinas_semana")
	public String verRutinaSemanal(Model modelo) {
		List<Rutina_Semanal> rutinasSemanales = repositorio.findAll();
		modelo.addAttribute("rutinas", rutinasSemanales);
		return "rutinas_semanales";
	}

	@GetMapping("/rutinas_semana/nuevo")
	public String mostrarFormulario(Model modelo) {
		List<Rutina> listaRutinas = repositorioRutina.findAll();
		Rutina_Semanal rutina = new Rutina_Semanal();
		modelo.addAttribute("rutinasSemanales", rutina);
		modelo.addAttribute("rutinas", listaRutinas);
		return "crear_rutina_semanal";
	}

	@PostMapping("/rutinas_semana")
	public String guardarRutinaSemanal(@ModelAttribute("rutina_Semanal") Rutina_Semanal rutina) {
		repositorio.save(rutina);
		return "redirect:/rutinas_semana";
	}

	@GetMapping("/rutinas_semana/{id_rutina}/editar")
	public String mostrarFormularioEditar(@PathVariable("id_rutina") Integer id_rutina, Model modelo) {
		List<Rutina> listaRutinas = repositorioRutina.findAll();
		modelo.addAttribute("rutinasSemanales", repositorio.findById(id_rutina).get());
		modelo.addAttribute("rutinas", listaRutinas);
		return "editar_rutina_semanal";
	}

	@PostMapping("/rutinas_semana/{id_rutina}")
	public String actualizarRutinaSemanal(@PathVariable("id") Integer id, @ModelAttribute("rutina_semanal") Rutina_Semanal rutina,
			Model modelo) {
		List<Rutina> listaRutinas = repositorioRutina.findAll();
		Rutina_Semanal rutinaActual = repositorio.findById(id).get();
		rutinaActual.setId_rutina_semanal(id);
		rutinaActual.setFecha_de_inicio(rutina.getFecha_de_inicio());
		rutinaActual.setFecha_de_fin(rutina.getFecha_de_fin());
		rutinaActual.setCliente(rutina.getCliente());
		rutinaActual.setRutinas(rutina.getRutinas());
		modelo.addAttribute("rutinas", listaRutinas);
		repositorio.save(rutinaActual);
		return "redirect:/rutinas_semana";
	}

	@GetMapping("/rutinas_semana/{id_rutina}")
	public String eliminarRutinaSemanal(@PathVariable("id_rutina") Integer id) {
		repositorio.deleteById(id);
		return "redirect:/rutinas_semana";
	}
}
