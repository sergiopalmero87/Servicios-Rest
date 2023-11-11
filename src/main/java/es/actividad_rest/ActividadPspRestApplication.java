package es.actividad_rest;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import es.actividad_rest.controlador.LibroControlador;
import es.actividad_rest.modelo.negocio.LibroService;

@SpringBootApplication
public class ActividadPspRestApplication {
	
	@Autowired
	private LibroService libroService;
	
	@Autowired
	private LibroControlador libroControlador;
	
	@Autowired
	private ApplicationContext context;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	

	public static void main(String[] args) {
		System.out.println("Cliente -> Cargando el contexto de Spring");
		SpringApplication.run(ActividadPspRestApplication.class, args);
	}

	

		
}


