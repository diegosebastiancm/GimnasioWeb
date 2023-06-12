package com.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.app.web.service.PersonaServicio;

@Controller
public class RegistroController {

    @Autowired
    private PersonaServicio servicio;
    
    @GetMapping("/login")
    public String iniciarSesion() {
        return "login";
    } 
   
}

