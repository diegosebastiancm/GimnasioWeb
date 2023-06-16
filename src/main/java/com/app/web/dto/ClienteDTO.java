package com.app.web.dto;

import java.math.BigDecimal;
import java.sql.Date;

import com.app.web.entities.Cliente;
import com.app.web.entities.Persona;

import lombok.Data;
@Data
public class ClienteDTO {
    private Integer id;
    private String documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private char genero;
    private String password;
    private BigDecimal peso;
    private BigDecimal altura;
    private BigDecimal imc;
    private Date fecha_inscripcion;
    private String plan;
    private Date fecha_nacimiento;
    private Integer edad;

    public ClienteDTO() {
    }

    public ClienteDTO(Persona persona, Cliente cliente) {
        this.id = persona.getId();
        this.documento = persona.getDocumento();
        this.nombre = persona.getNombre();
        this.apellido = persona.getApellido();
        this.telefono = persona.getTelefono();
        this.email = persona.getEmail();
        this.genero = persona.getGenero();
        this.peso = cliente.getPeso();
        this.altura = cliente.getAltura();
        this.imc = cliente.getImc();
        this.fecha_inscripcion = cliente.getFecha_inscripcion();
        this.plan = cliente.getPlan();
        this.fecha_nacimiento = cliente.getFecha_nacimiento();
        this.edad = cliente.getEdad();
    }
}
