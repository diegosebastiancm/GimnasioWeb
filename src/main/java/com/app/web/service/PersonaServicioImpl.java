package com.app.web.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.web.entities.Persona;
import com.app.web.repository.PersonaRepository;

@Service
public class PersonaServicioImpl implements PersonaServicio {

    private PersonaRepository personaRepository;
    private PasswordEncoder passwordEncoder;
 
    @Autowired
    public PersonaServicioImpl(PersonaRepository personaRepository, PasswordEncoder passwordEncoder) {
        this.personaRepository = personaRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public Persona guardar(Persona personaRegistrar) {     
        personaRegistrar.setPassword(passwordEncoder.encode(personaRegistrar.getPassword()));
        return personaRepository.save(personaRegistrar);
    }
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Persona persona = personaRepository.findByEmail(username);
        if (persona == null) {
            throw new UsernameNotFoundException("Usuario o password inválidos");
        }
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority(persona.getRol().name()));
        return new User(persona.getEmail(), persona.getPassword(), authorities);
    }
	@Override
    public List<Persona> listarPersonas() {
        return personaRepository.findAll();
    }
}
