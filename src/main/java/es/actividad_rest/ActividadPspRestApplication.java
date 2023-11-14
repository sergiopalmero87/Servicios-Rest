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
import es.actividad_rest.modelo.entidad.Libro;
import es.actividad_rest.modelo.negocio.LibroService;

@SpringBootApplication
public class ActividadPspRestApplication { //implements CommandLineRunner {
	
	
	private LibroService libroService;
	
	@Autowired
	private LibroControlador libroControlador;
	
	@Autowired
	private ApplicationContext context;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Autowired
	public ActividadPspRestApplication(LibroService libroService) {
	    this.libroService = libroService;
	}

	

	public static void main(String[] args) {
		System.out.println("Cliente -> Cargando el contexto de Spring");
		SpringApplication.run(ActividadPspRestApplication.class, args);
	}



	/*public void menu() {
		System.out.println("\n=== Menú ===");
        System.out.println("1. Dar de alta un libro");
        System.out.println("2. Eliminar un libro por ID");
        System.out.println("3. Modificar un libro por ID");
        System.out.println("4. Obtener un libro por ID");
        System.out.println("5. Listar todos los libros");
        System.out.println("6. Salir");
	}*/



	/*@Override
	public void run(String... args) throws Exception {
		Scanner scan = new Scanner(System.in);
		int opcion = 0;
		
		do {
			menu();
			System.out.print("Elige una opcion: \n");
			opcion = scan.nextInt();
			
			switch(opcion) {
				case 1: 
					System.out.println("Introduce los datos de la nueva pelicula:");
					System.out.print("ID: ");
					int id = scan.nextInt();
					scan.nextLine();
					System.out.print("TITULO: ");
					String titulo = scan.nextLine();
					System.out.print("EDITORIAL: ");
					String editorial = scan.nextLine();
					System.out.print("NOTA");
					double nota = scan.nextDouble();
					Libro libro = new Libro(id,titulo, editorial, nota);
					libroService.altaLibroRs(libro);
					break;
				case 2:
					System.out.print("Introduce el ID del libro a eliminar: ");
                    int idEliminar = scan.nextInt();
                    libroService.eliminarLibroPorIdRs(idEliminar);
                    break;
				case 3:
					System.out.println("Introduce los datos de la pelicula a editar:");
					System.out.print("ID: ");
                    int idEditar = scan.nextInt();
                    scan.nextLine();  // Consumir el salto de línea pendiente
                    System.out.print("TITULO: ");
                    String nuevoTitulo = scan.nextLine();
                    System.out.print("EDITORIAL: ");
                    String nuevaEditorial = scan.nextLine();
                    System.out.print("NOTA: ");
                    double nuevaNota = scan.nextDouble();
                    Libro libroEditado = new Libro(idEditar, nuevoTitulo, nuevaEditorial, nuevaNota);
                    libroService.editarLibroRs(libroEditado);
                    break;
				case 4:
					System.out.print("Introduce el ID del libro a consultar: ");
                    int idConsultar = scan.nextInt();
                    libroService.obtenerLibroPorIdRs(idConsultar);
                    break;
				case 5:
					System.out.println("La lista de libros es: ");
					libroService.obtenerTodosLosLibrosRs();
					break;
				case 6:
					System.out.println("Saliendo de la aplicacion!");
					pararAplicacion();
					break;
				default:
					System.out.println("Opcion no valida. Intentelo de nuevo.");
				
			}
		
		}while(opcion != 6);
		
		
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


