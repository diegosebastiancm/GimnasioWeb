package com.app.web.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.app.web.entities.Cliente;
import com.app.web.entities.Ejercicio;
import com.app.web.entities.Ejercicio_Realizado;
import com.app.web.entities.Rutina;
import com.app.web.entities.Rutina_Semanal;
import com.app.web.repository.ClienteRepository;
import com.app.web.repository.EjercicioRepository;
import com.app.web.repository.Ejercicio_RealizadoRepository;
import com.app.web.repository.RutinaRepository;
import com.app.web.repository.RutinaSemanalRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	ClienteRepository repositorio;

	@Autowired
	RutinaRepository repositorioRutina;

	@Autowired
	RutinaSemanalRepository repositorioSemana;
	
	@Autowired
	EjercicioRepository repositorioEjercicio;
	
	@Autowired
	Ejercicio_RealizadoRepository repositorioHistorial;

	@GetMapping()
	public String inicio(Model modelo, HttpSession session){
		Integer clienteId =  (Integer) session.getAttribute("clienteId");
		Cliente cliente = repositorio.findById(clienteId).get();
		modelo.addAttribute("cliente", cliente);
	    return "inicio_cliente";
	}
	
	@GetMapping("/{id}/rutinasSemanales")
	public String verRutinaSemanal(@PathVariable("id") Integer id, Model modelo) {
		Cliente cliente = repositorio.findById(id).get();
		List<Rutina_Semanal> rutinasSemanales = repositorioSemana.findByCliente(repositorio.findById(id).get());
		modelo.addAttribute("rutinasSemanales", rutinasSemanales);
		modelo.addAttribute("cliente", cliente);
		return "cliente_semanas";
	}

	@GetMapping("/{id}/rutinasSemanales/{id_rutina}")
	public String verRutinaDiaria(@PathVariable("id") Integer id, @PathVariable("id_rutina") Integer id_rutina,
			Model modelo) {
		Cliente cliente = repositorio.findById(id).get();
		List<Rutina_Semanal> rutinasSemanales = repositorioSemana.findByCliente(repositorio.findById(id).get());
		Rutina_Semanal rutinaSemana = null;
		for (Rutina_Semanal rutina : rutinasSemanales) {
			if (rutina.getId_rutina_semanal().equals(id_rutina)) {
				rutinaSemana = rutina;
				break;
			}
		}
		List<Rutina> rutinasDiarias = repositorioRutina.findByRutinaSemanal(rutinaSemana);
		modelo.addAttribute("cliente", cliente);
		modelo.addAttribute("rutinasDiarias", rutinasDiarias);
		return "cliente_rutinas";
	}

	@GetMapping("/{id}/rutinasSemanales/{id_rutina}/{id_dia}")
	public String verRutinaDiaria(@PathVariable("id") Integer id, @PathVariable("id_rutina") Integer id_rutina,
			@PathVariable("id_dia") Integer dia, Model modelo) {
		List<Rutina_Semanal> rutinasSemanales = repositorioSemana.findByCliente(repositorio.findById(id).get());
		Rutina_Semanal rutinaSemana = null;
		for (Rutina_Semanal rutina : rutinasSemanales) {
			if (rutina.getId_rutina_semanal().equals(id_rutina)) {
				rutinaSemana = rutina;
				break;
			}
		}
		List<Rutina> rutinasDiarias = repositorioRutina.findByRutinaSemanal(rutinaSemana);
		Rutina rutinaDiaria = null;
		for (Rutina rutina : rutinasDiarias) {
			if (rutina.getId_rutina_diaria().equals(dia)) {
				rutinaDiaria = rutina;
				break;
			}
		}
		List<Ejercicio> ejerciciosRutina = repositorioEjercicio.findByRutina(rutinaDiaria);
		Cliente cliente = repositorio.findById(id).get();
		modelo.addAttribute("cliente", cliente);
		modelo.addAttribute("rutinasDia", rutinaDiaria);
		modelo.addAttribute("ejerciciosRutina", ejerciciosRutina);
		return "cliente_rutina_dia";
	}
	
	@GetMapping("/{id}/historialEjercicios")
	public String verHistorialEjercicios(@PathVariable("id") Integer id, Model modelo) {
		Cliente cliente =  repositorio.findByPersona_id(id);
		List<Ejercicio_Realizado> ejerciciosRealizados = repositorioHistorial.findByCliente(cliente);
		modelo.addAttribute("cliente", cliente);
		modelo.addAttribute("ejerciciosRealizados", ejerciciosRealizados);
		return "cliente_historial";
	}

}
