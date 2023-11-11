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
public class ActividadPspRestApplication implements CommandLineRunner{
	
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

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea después de leer el número

            switch (opcion) {
                case 1:
                    // Dar de alta un libro
                    // ...
                    break;
                case 2:
                    // Dar de baja un libro por ID
                    // ...
                    break;
                case 3:
                    // Modificar un libro por ID
                    // ...
                    break;
                case 4:
                    // Obtener un libro por ID
                    // ...
                    break;
                case 5:
                    // Listar todos los libros
                    // ...
                    break;
                case 6:
                    System.out.println("Saliendo de la aplicación.");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }

        } while (opcion != 6);
    }

    private static void mostrarMenu() {
        System.out.println("Menú:");
        System.out.println("1. Dar de alta un libro");
        System.out.println("2. Dar de baja un libro por ID");
        System.out.println("3. Modificar un libro por ID");
        System.out.println("4. Obtener un libro por ID");
        System.out.println("5. Listar todos los libros");
        System.out.println("6. Salir");
        System.out.print("Selecciona una opción: ");
    }

		
}


