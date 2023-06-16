package com.app.web.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.web.dto.ClienteDTO;
import com.app.web.dto.EntrenadorDTO;
import com.app.web.entities.Cliente;
import com.app.web.entities.Entrenador;
import com.app.web.entities.Persona;
import com.app.web.entities.Rol;
import com.app.web.repository.ClienteRepository;
import com.app.web.repository.EntrenadorRepository;
import com.app.web.repository.PersonaRepository;
import com.app.web.service.PersonaServicio;

@Controller
@RequestMapping("/admin")
public class AdministradorController {
	@Autowired
	PersonaRepository personaRepository;
	@Autowired
	PersonaServicio personaServicio;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	EntrenadorRepository entrenadorRepository;

	@GetMapping()
	public String listar(Model model) {
		List<Integer> clienteIds = clienteRepository.findAll().stream().map(Cliente::getPersona_id)
				.collect(Collectors.toList());
		List<Persona> clientes = personaRepository.findAllById(clienteIds);
		model.addAttribute("clientes", clientes);
		List<Integer> entrenadorIds = entrenadorRepository.findAll().stream().map(Entrenador::getPersona_id)
				.collect(Collectors.toList());
		List<Persona> entrenadores = personaRepository.findAllById(entrenadorIds);
		model.addAttribute("entrenadores", entrenadores);
		return "index_admin";
	}
	
	@GetMapping("/editarCliente/{id}")
	public String mostrarFormularioEditarCliente(@PathVariable Integer id, Model modelo) {
	    Persona persona = personaRepository.findById(id).get();
	    String[] planes = { "BÁSICO", "PERSONALIZADO" };
		Character[] generos = { 'F', 'M' };
		modelo.addAttribute("planes", planes);
		modelo.addAttribute("generos", generos);
	    Cliente cliente = clienteRepository.findByPersona_id(persona.getId());  
        ClienteDTO clienteDTO = new ClienteDTO(persona, cliente);
	    modelo.addAttribute("clienteDTO", clienteDTO);
	    return "editar_cliente";
	}

	@PostMapping("/editarCliente/{id}")
	public String guardarCambiosCliente(@ModelAttribute ClienteDTO clienteDTO) {
		Persona persona = personaRepository.findById(clienteDTO.getId()).get();
	    Cliente cliente = persona.getCliente();
	    persona.setDocumento(clienteDTO.getDocumento());
	    persona.setNombre(clienteDTO.getNombre());
	    persona.setApellido(clienteDTO.getApellido());
	    persona.setTelefono(clienteDTO.getTelefono());
	    persona.setEmail(clienteDTO.getEmail());
	    persona.setGenero(clienteDTO.getGenero());
	    cliente.setPeso(clienteDTO.getPeso());
	    cliente.setAltura(clienteDTO.getAltura());
	    cliente.setEdad(clienteDTO.getEdad());
	    cliente.setFecha_nacimiento(clienteDTO.getFecha_nacimiento());
	    cliente.setFecha_inscripcion(clienteDTO.getFecha_inscripcion());
	    cliente.setPlan(clienteDTO.getPlan());
	    personaRepository.save(persona);
	    clienteRepository.save(cliente);
	    return "redirect:/admin";	}

	@GetMapping({ "/registrarCliente" })
	public String mostrarFormulario(Model modelo) {
		Persona persona = new Persona();
		modelo.addAttribute("persona", persona);
		String[] planes = { "BÁSICO", "PERSONALIZADO" };
		Character[] generos = { 'F', 'M' };
		modelo.addAttribute("planes", planes);
		modelo.addAttribute("generos", generos);
		return "registrar_cliente";
	}

	@PostMapping("/registrarCliente")
	public String registrar(@ModelAttribute("persona") Persona persona, @RequestParam("peso") BigDecimal peso,
			@RequestParam("altura") BigDecimal altura, @RequestParam("fecha_inscripcion") Date fecha_inscripcion,
			@RequestParam("fecha_nacimiento") Date fecha_nacimiento, @RequestParam("plan") String plan,
			@RequestParam("edad") Integer edad) {
		Rol rol = Rol.CLIENTE;
		persona.setRol(rol);
		Cliente cliente = new Cliente();
		cliente.setPeso(peso);
		cliente.setAltura(altura);
		cliente.setImc(cliente.getImc());
		cliente.setFecha_inscripcion(fecha_inscripcion);
		cliente.setFecha_nacimiento(fecha_nacimiento);
		cliente.setEdad(edad);
		cliente.setPlan(plan);
		persona.setCliente(cliente);
		cliente.setPersona(persona);
		personaServicio.guardar(persona);
		clienteRepository.save(cliente);
		return "redirect:/admin/registrarCliente?exito";
	}

	@GetMapping("/eliminarCliente/{id}")
	public String eliminarCliente(@PathVariable Integer id, Model modelo) {
		clienteRepository.deleteById(id);
		Cliente cliente = clienteRepository.findByPersona_id(id);
		if (cliente.getEjercicioRealizado() != null) {
			cliente.getEjercicioRealizado().clear();
		}
		if (cliente.getRutina_semanal() != null) {
			cliente.getRutina_semanal().clear();
		}
		return "redirect:/admin";
	}

	@GetMapping({ "/registrarEntrenador" })
	public String mostrarFormularioEntrenador(Model modelo) {
		Persona persona = new Persona();
		modelo.addAttribute("persona", persona);
		Character[] generos = { 'F', 'M' };
		modelo.addAttribute("generos", generos);
		return "registrar_entrenador";
	}

	@PostMapping("/registrarEntrenador")
	public String registrarEntreador(@ModelAttribute("persona") Persona persona,
			@RequestParam("direccion") String direccion) {
		Entrenador entrenador = new Entrenador();
		entrenador.setDireccion(direccion);
		persona.setEntrenador(entrenador);
		entrenador.setPersona(persona);
		personaServicio.guardar(persona);
		entrenadorRepository.save(entrenador);
		return "redirect:/admin/registrarEntrenador?exito";
	}

	@GetMapping({ "/editarEntrenador/{id}" })
	public String mostrarFormularioEditarEntrenador(@PathVariable Integer id, Model modelo) {
		Optional<Entrenador> entrenadorOptional = entrenadorRepository.findById(id);
		Entrenador entrenador = entrenadorOptional.get();
		Character[] generos = { 'F', 'M' };
		modelo.addAttribute("generos", generos);
		EntrenadorDTO entrenadorDTO = new EntrenadorDTO();
		entrenadorDTO.setId(entrenador.getPersona().getId());
		entrenadorDTO.setDocumento(entrenador.getPersona().getDocumento());
		entrenadorDTO.setNombre(entrenador.getPersona().getNombre());
		entrenadorDTO.setApellido(entrenador.getPersona().getApellido());
		entrenadorDTO.setTelefono(entrenador.getPersona().getTelefono());
		entrenadorDTO.setEmail(entrenador.getPersona().getEmail().toString());
		entrenadorDTO.setGenero(entrenador.getPersona().getGenero());
		entrenadorDTO.setDireccion(entrenador.getDireccion());
		modelo.addAttribute("entrenadorDTO", entrenadorDTO);
		return "editar_entrenador";
	}

	@PostMapping("/editarEntrenador/{id}")
	public String guardarCambiosEntrenador(@ModelAttribute("entrenadorDTO") EntrenadorDTO entrenadorDTO) {
		Optional<Entrenador> entrenadorOptional = entrenadorRepository.findById(entrenadorDTO.getId());
		if (entrenadorOptional.isPresent()) {
			Entrenador entrenador = entrenadorOptional.get();
			Persona persona = entrenador.getPersona();
      		persona.setDocumento(entrenadorDTO.getDocumento());
			persona.setNombre(entrenadorDTO.getNombre());
			persona.setApellido(entrenadorDTO.getApellido());
			persona.setTelefono(entrenadorDTO.getTelefono());
			persona.setEmail(entrenadorDTO.getEmail());
			persona.setGenero(entrenadorDTO.getGenero());
			entrenador.setDireccion(entrenadorDTO.getDireccion());
			entrenadorRepository.save(entrenador);
			personaRepository.save(persona);
		}
		return "redirect:/admin";
	}

	@GetMapping("/eliminarEntrenador/{id}")
	public String eliminarEntrenador(@PathVariable Integer id, Model modelo) {
		entrenadorRepository.deleteById(id);
		return "redirect:/admin";
	}
}
