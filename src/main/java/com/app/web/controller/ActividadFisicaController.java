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
import com.app.web.entities.Rutina;
import com.app.web.repository.ActividadFisicaRepository;

public class ActividadFisicaController {

	@Autowired
	ActividadFisicaRepository repositorio;

	@GetMapping("/actividades")
	public String verActividad(@PathVariable("id") Integer id, Model modelo) {
		List<Actividad_Fisica> actividades = repositorio.findAll();
		modelo.addAttribute("actividades", actividades);
		return "actividades";
	}

	@GetMapping("/actividades/nuevo")
	public String mostrarFormulario(Model modelo) {
		Actividad_Fisica actividad = new Actividad_Fisica();
		modelo.addAttribute("actividad", actividad);
		return "crear_actividad";
	}

	@PostMapping("/actividades")
	public String guardarActividad(@ModelAttribute("actividad_fisica") Actividad_Fisica actividad) {
		repositorio.save(actividad);
		return "redirect:/actividades";
	}

	@GetMapping("/actividades/{id}/editar")
	public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model modelo) {
		modelo.addAttribute("actividades", repositorio.findById(id).get());
		return "editar_actividad";
	}

	@PostMapping("/actividades/{id}")
	public String actualizarActividad(@PathVariable("id") Integer id,
			@ModelAttribute("actividad_fisica") Actividad_Fisica actividad, Model modelo) {
		Actividad_Fisica actividadActual = repositorio.findById(id).get();
		actividadActual.setId_actividad(id);
		actividadActual.setNombre_actividad(actividad.getNombre_actividad());
		actividadActual.setLink(actividad.getLink());
		repositorio.save(actividadActual);
		return "redirect:/actividades";
	}

	@GetMapping("/actividades/{id}")
	public String eliminarActividad(@PathVariable("id") Integer id) {
		repositorio.deleteById(id);
		return "redirect:/actividades";
	}
}
