package com.app.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.app.web.repository.RutinaSemanalRepository;

@Controller
public class RutinaSemanalController {

	@Autowired
	RutinaSemanalRepository repositorio;
}
