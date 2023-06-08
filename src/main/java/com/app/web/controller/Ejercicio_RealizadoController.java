package com.app.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.entities.Actividad_Fisica;
import com.app.web.entities.Cliente;
import com.app.web.entities.Ejercicio_Realizado;
import com.app.web.repository.Actividad_FisicaRepository;
import com.app.web.repository.ClienteRepository;
import com.app.web.repository.Ejercicio_RealizadoRepository;

@Controller
public class Ejercicio_RealizadoController {
	@Autowired
	private Ejercicio_RealizadoRepository ejercicio_RealizadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private Actividad_FisicaRepository actividadRepository;

	@GetMapping({ "/ejerciciosRealizados", "/ejerciciosRealizados/listar" })
	public String listar(Model model) {
		List<Ejercicio_Realizado> ejerciciosRealizados = ejercicio_RealizadoRepository.findAll();
		model.addAttribute("ejerciciosRealizados", ejerciciosRealizados);
		return "index_ejercicioRealizado";
	}

	@GetMapping("/ejercicioRealizado/registrar")
	public String mostrarFormulario(Model modelo) {
		Ejercicio_Realizado ejercicioRealizado = new Ejercicio_Realizado();
		modelo.addAttribute("ejercicioRealizado", ejercicioRealizado);
		return "formulario_ejercicioRealizado";
	}

	@GetMapping("/ejercicioRealizado/ver/{id}")
	public String mostrarVerEjercicioRealizado(@PathVariable Integer id, Model modelo) {
		Ejercicio_Realizado ejercicioRealizado = ejercicio_RealizadoRepository.findById(id).get();
		modelo.addAttribute("ejercicioRealizado", ejercicioRealizado);
		return "ver_ejercicioRealizados";
	}

	@PostMapping({ "/ejerciciosRealizados", "/ejerciciosRealizados/listar" })
	public String guardarEjercicioRealizado(
			@ModelAttribute("ejercicioRealizado") Ejercicio_Realizado ejercicioRealizado) {
		ejercicio_RealizadoRepository.save(ejercicioRealizado);
		return "redirect:/ejerciciosRealizados";
	}

	@GetMapping("/ejercicioRealizado/editar/{id}")
	public String mostrarFormularioEditar(@PathVariable Integer id, Model modelo) {
		Ejercicio_Realizado ejercicioRealizado = ejercicio_RealizadoRepository.findById(id).get();
		modelo.addAttribute("ejercicioRealizado", ejercicioRealizado);
		return "formulario_ejercicioRealizado";
	}

	@PostMapping("/ejercicioRealizado/{id}")
	public String actualizarEjercicioRealizado(@PathVariable Integer id,
			@ModelAttribute("ejercicio_Realizado") Ejercicio_Realizado ejercicio_Realizado, Model modelo) {
		Ejercicio_Realizado ejercicioRealizadoExistente = ejercicio_RealizadoRepository.findById(id).get();
		ejercicioRealizadoExistente.setPeso_levantado(ejercicio_Realizado.getPeso_levantado());
		ejercicioRealizadoExistente.setRepeticiones_realizadas(ejercicio_Realizado.getRepeticiones_realizadas());
		ejercicioRealizadoExistente.setSeries_realizadas(ejercicio_Realizado.getSeries_realizadas());
		ejercicio_RealizadoRepository.save(ejercicioRealizadoExistente);
		return "redirect:/ejerciciosRealizados";
	}

	@GetMapping("/ejercicioRealizado/{id}")
	public String eliminarEjercicioRealizado(@PathVariable Integer id, Model modelo) {
		ejercicio_RealizadoRepository.deleteById(id);
		return "redirect:/ejerciciosRealizados";
	}

	@GetMapping("/verEjercicioRealizado")
	public String verEjercicioRealizado() {
		return "redirect:/ejerciciosRealizados";
	}
}
