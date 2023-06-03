package com.app.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.app.web.entities.Actividad_Fisica;
import com.app.web.entities.Ejercicio;
import com.app.web.repository.ActividadFisicaRepository;
import com.app.web.repository.EjercicioRepository;

public class EjercicioController {

	@Autowired
	EjercicioRepository repositorio;
	
	@Autowired
	ActividadFisicaRepository repositorioActividad;
	
	@GetMapping("/ejercicios")
	public String verRutina(Model modelo) {
		List<Ejercicio> ejercicios = repositorio.findAll();
		modelo.addAttribute("ejercicios", ejercicios);
		return "ejercicios";
	}

	@GetMapping("/ejercicios/nuevo")
	public String mostrarFormulario(Model modelo) {
		List<Actividad_Fisica> listaActividades = repositorioActividad.findAll();
		Ejercicio ejercicio = new Ejercicio();
		modelo.addAttribute("ejercicio", ejercicio);
		modelo.addAttribute("actividades", listaActividades);
		return "crear_ejercicio";
	}

	@PostMapping("/ejercicios")
	public String guardarEjercicio(@ModelAttribute("ejericio") Ejercicio ejercicio) {
		repositorio.save(ejercicio);
		return "redirect:/ejercicios";
	}

	@GetMapping("/ejercicios/{id}/editar")
	public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model modelo) {
		List<Actividad_Fisica> listaActividades = repositorioActividad.findAll();
		modelo.addAttribute("ejercicios", repositorio.findById(id).get());
		modelo.addAttribute("actividades", listaActividades);
		return "editar_ejercicio";
	}

	@PostMapping("/ejercicios/{id}")
	public String actualizarEjercicio(@PathVariable("id") Integer id, @ModelAttribute("ejercicio") Ejercicio ejercicio,
			Model modelo) {
		List<Actividad_Fisica> listaActividades = repositorioActividad.findAll();
		Ejercicio ejercicioActual = repositorio.findById(id).get();
		ejercicioActual.setId_ejercicio(id);
		ejercicioActual.setEjercicio(ejercicio.getEjercicio());
		ejercicioActual.setPeso_ejercicio(ejercicio.getPeso_ejercicio());
		ejercicioActual.setRepeticiones(ejercicio.getRepeticiones());
		ejercicioActual.setSeries(ejercicio.getSeries());
		ejercicioActual.setRutina(ejercicio.getRutina());
		modelo.addAttribute("ejercicios", listaActividades);
		repositorio.save(ejercicioActual);
		return "redirect:/ejercicios";
	}

	@GetMapping("/rutinas/{id}")
	public String eliminarEjercicio(@PathVariable("id") Integer id) {
		repositorio.deleteById(id);
		return "redirect:/ejercicios";
	}
}
