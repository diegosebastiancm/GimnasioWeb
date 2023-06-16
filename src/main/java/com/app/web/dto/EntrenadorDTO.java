package com.app.web.dto;

import lombok.Data;

@Data
public class EntrenadorDTO {
    private Integer id;
    private String documento;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private Character genero;
    private String direccion;
    private String password;
}
