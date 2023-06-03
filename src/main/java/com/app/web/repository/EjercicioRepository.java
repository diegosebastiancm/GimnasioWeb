package com.app.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.web.entities.Ejercicio;

public interface EjercicioRepository extends JpaRepository<Ejercicio, Integer> {

}
