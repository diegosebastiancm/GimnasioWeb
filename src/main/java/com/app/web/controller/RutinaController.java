package com.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entities.Ejercicio;
import com.app.web.entities.Rutina;
import com.app.web.repository.EjercicioRepository;
import com.app.web.repository.RutinaRepository;

@Controller
public class RutinaController {

	@Autowired
	RutinaRepository repositorio;

	@Autowired
	EjercicioRepository repositorioEjercicio;

	/*
	 * @GetMapping("/cliente/{id}/rutinas") public String
	 * verRutina(@PathVariable("id") Integer id, Model modelo) { List<Rutina>
	 * rutinas = repositorio.findByClienteId(id); modelo.addAttribute("rutinas",
	 * rutinas); return "cliente_rutinas"; } esto iria en el controlador del cliente
	 */

	@GetMapping("/rutinas")
	public String verRutina(Model modelo) {
		List<Rutina> rutinas = repositorio.findAll();
		modelo.addAttribute("rutinas", rutinas);
		return "index_rutina";
	}

	@GetMapping("/rutinas/nuevo")
	public String mostrarFormulario(Model modelo) {
		List<Ejercicio> listaEjercicios = repositorioEjercicio.findAll();
		Rutina rutina = new Rutina();
		modelo.addAttribute("rutinas", rutina);
		modelo.addAttribute("ejercicios", listaEjercicios);
		return "crear_rutina";
	}

	@PostMapping("/rutinas")
	public String guardarRutina(@ModelAttribute("rutina") Rutina rutina) {
		repositorio.save(rutina);
		return "redirect:/rutinas";
	}

	@GetMapping("/rutinas/{id_rutina}/editar")
	public String mostrarFormularioEditar(@PathVariable("id_rutina") Integer id_rutina, Model modelo) {
		List<Ejercicio> listaEjercicios = repositorioEjercicio.findAll();
		modelo.addAttribute("rutinas", repositorio.findById(id_rutina).get());
		modelo.addAttribute("ejercicios", listaEjercicios);
		return "editar_rutina";
	}

	@PostMapping("/rutinas/{id_rutina}")
	public String actualizarRutina(@PathVariable("id_rutina") Integer id, @ModelAttribute("rutina") Rutina rutina,
			Model modelo) {
		List<Ejercicio> listaEjercicios = repositorioEjercicio.findAll();
		Rutina rutinaActual = repositorio.findById(id).get();
		rutinaActual.setId_rutina_diaria(id);
		rutinaActual.setDia_semana(rutina.getDia_semana());
		rutinaActual.setRutinaSemanal(rutina.getRutinaSemanal());
		rutinaActual.setObjetivo(rutina.getObjetivo());
		rutinaActual.setEjercicios(rutina.getEjercicios());
		modelo.addAttribute("ejercicios", listaEjercicios);
		repositorio.save(rutinaActual);
		return "redirect:/rutinas";
	}

	@GetMapping("/rutinas/{id_rutina}")
	public String eliminarRutina(@PathVariable("id_rutina") Integer id) {
		repositorio.deleteById(id);
		return "redirect:/rutinas";
	}
	
	@GetMapping("/rutinas/{id}/ejercicios")
	public String verEjerciciosRutina(@PathVariable("id")Integer id, Model modelo) {
		Rutina rutina = repositorio.findById(id).get();
		List<Ejercicio> listaEjercicios = repositorioEjercicio.findByRutina(rutina);
		modelo.addAttribute("ejercicios", listaEjercicios);
		return "index_ejercicio";
	}
	

	/*
	 * @GetMapping("/cliente/{id}/rutina/historico") public String verHistorico() {
	 * return ""; } esto tmb iria en el controlador del cliente....o de ejercicio
	 */
}
