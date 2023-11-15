package es.actividad_rest;

import java.util.List;
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
import es.actividad_rest.modelo.entidad.Libro;
import es.actividad_rest.modelo.negocio.LibroService;

@SpringBootApplication
public class ActividadPspRestApplication implements CommandLineRunner { 
	
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
		System.out.println("****** Arrancando el cliente REST ******");		
		
		logica();
		
		
	}
	
	
	
	
	//METODOS
	public void menu() {
		System.out.println("\n=== Menú ===");
        System.out.println("1. Dar de alta un libro");
        System.out.println("2. Eliminar un libro por ID");
        System.out.println("3. Modificar un libro por ID");
        System.out.println("4. Obtener un libro por ID");
        System.out.println("5. Listar todos los libros");
        System.out.println("6. Salir");
	}
	
	
	public void logica() {
		Scanner sc = new Scanner(System.in);
		int opcion;
		do {
			menu();
			System.out.println("Elige una opcion entre 1 y 6:");
			opcion = sc.nextInt();
			
			switch(opcion) {
				case 1: 
					System.out.println("ID: ");
					int id = sc.nextInt();
					sc.nextLine();
					
					System.out.println("TITULO: ");
					String titulo = sc.nextLine();
					
					System.out.println("EDITORIAL: ");
					String editorial = sc.nextLine();
					
					System.out.println("NOTA: ");
					double nota = sc.nextDouble();
					
					Libro libro = new Libro(id, titulo, editorial, nota);
					libroService.altaLibroRs(libro);
					
					System.out.println("Libro añadido");
					break;
					
				case 2:
					System.out.println("ID del libro a eliminar: ");
					int idEliminar = sc.nextInt();
					libroService.eliminarLibroPorIdRs(idEliminar); 
					break;
					
				case 3:
					System.out.println("ID: ");
					int idNuevo = sc.nextInt();
					sc.nextLine();
					
					System.out.println("TITULO: ");
					String tituloNuevo = sc.nextLine();
					
					System.out.println("EDITORIAL: ");
					String editorialNueva = sc.nextLine();
					
					System.out.println("NOTA: ");
					double notaNueva = sc.nextDouble();
					
					Libro libroNuevo = new Libro(idNuevo, tituloNuevo, editorialNueva, notaNueva);
					libroService.editarLibroRs(libroNuevo);
					break;
					
				case 4:
					System.out.println("ID del libro a obtener: ");
					int idObtener = sc.nextInt();
					Libro l = libroService.obtenerLibroPorIdRs(idObtener); 
					System.out.println(l);
					break;
					
				case 5:
					List<Libro> listaLibros;
					listaLibros = libroService.obtenerTodosLosLibrosRs();
					System.out.println(listaLibros);
					break;
					
				case 6:
					System.out.println("Saliendo de la aplicacion...");
					pararAplicacion();
					break;
					
				default:
					System.out.println("Opción incorrecta. Elija una opción válida");
					break;
					
			}
				
		}while (opcion != 6);
		
	}
	
	
	public void pararAplicacion() {
		//Esta aplicacion levanta un servidor web, por lo que tenemos que dar 
		//la orden de pararlo cuando acabemos. Para ello usamos el método exit, 
		//de la clase SpringApplication, que necesita tanto el contexto de 
		//Spring como un objeto que implemente la interfaz ExitCodeGenerator. 
		//Podemos usar la función lambda "() -> 0" para simplificar 
		
		SpringApplication.exit(context, () -> 0);
		
		//Podemos hacerlo también de una manera clásica, es decir, creando
		//la clase anónima a partir de la interfaz. 
		//El código de abajo sería equivalente al de arriba
		//(pero mucho más largo)
		/*
		SpringApplication.exit(context, new ExitCodeGenerator() {
			
			@Override
			public int getExitCode() {
				return 0;
			}
		});*/
	
	}
		
}


