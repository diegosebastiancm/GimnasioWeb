package com.app.web.security;


import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.app.web.entities.Cliente;
import com.app.web.entities.Entrenador;
import com.app.web.entities.Persona;
import com.app.web.repository.ClienteRepository;
import com.app.web.repository.EntrenadorRepository;
import com.app.web.repository.PersonaRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@Configuration
public class SecurityConfiguration {
	 @Autowired
	    private PersonaRepository personaRepository;

	    @Autowired
	    private EntrenadorRepository entrenadorRepository;

	    @Autowired
	    private ClienteRepository clienteRepository;

	    @SuppressWarnings({ "deprecation", "removal" })
		@Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    	 http.authorizeRequests()
	            .requestMatchers("/registro").permitAll()
	            .requestMatchers("/images/**").permitAll()
	            .requestMatchers("/css/**").permitAll()
	            .requestMatchers("/admin/**").hasAuthority("ADMIN")
	            .requestMatchers("/cliente/**").hasAuthority("CLIENTE")
	            .requestMatchers("/entrenador/**").hasAuthority("ENTRENADOR")
	            .anyRequest().authenticated()
	            .and()
	            .formLogin()
	            .loginPage("/login")
	            .successHandler((request, response, authentication) -> {
	                redirectAfterLogin(authentication, response, request);
	            })
	            .permitAll()
	            .and()
	            .logout()
	            .invalidateHttpSession(true)
	            .clearAuthentication(true)
	            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
	            .logoutSuccessUrl("/login?logout")
	            .permitAll();
	    	 
			return http.build();
	    }

	    private void redirectAfterLogin(Authentication authentication, HttpServletResponse response, HttpServletRequest request) throws IOException {
	        String username = authentication.getName();
	        Persona persona = personaRepository.findByEmail(username);
	        Cliente cliente = clienteRepository.findByPersona_id(persona.getId());
	        Entrenador entrenador = entrenadorRepository.findByPersona_id(persona.getId());
	        
	        if (cliente != null) {
	        	HttpSession session = request.getSession();
	            session.setAttribute("clienteId", cliente.getPersona_id());
	            response.sendRedirect("/cliente");
	            return;
	        }
	        if (entrenador != null) {
	            response.sendRedirect("/entrenador");
	            return;
	        }
	        if (persona != null) {
	            response.sendRedirect("/admin");
	            return;
	        }
	    }

	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
