package es.actividad_rest.modelo.negocio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.actividad_rest.modelo.entidad.Libro;

@Service
public class LibroService {

	public List<Libro> listaLibros;
	public int contador;
	
	//La URL base del servicio REST de libros
	public static final String URL = "http://localhost:8080/libros";
	
	@Autowired
	RestTemplate restTemplate;

	public LibroService() {
		System.out.println("\nLibroService -> Creando la lista de libros...");
		listaLibros = new ArrayList<Libro>();
		Libro l1 = new Libro(0, "Libro 0", "Editorial 0", 10.0);
		Libro l2 = new Libro(1, "Libro 1", "Editorial 1", 12.0);
		Libro l3 = new Libro(2, "Libro 2", "Editorial 2", 14.0);
		Libro l4 = new Libro(3, "Libro 3", "Editorial 3", 16.0);
		Libro l5 = new Libro(4, "Libro 4", "Editorial 4", 18.0);
		listaLibros.add(l1);
		listaLibros.add(l2);
		listaLibros.add(l3);
		listaLibros.add(l4);
		listaLibros.add(l5);
		System.out.println("LibroService -> ¡Creada lista de libros!\n");
	}
	
	//Metodos CRUD.
	//Obtener todos los libros.
	public List<Libro> obtenerTodosLosLibros() {
        return listaLibros;
    }
	
	//Obtener libros con restTemplate
	public List<Libro> obtenerTodosLosLibrosRs(){
		try {
			ResponseEntity<Libro[]> obtenerTodosLosLibros =
					  restTemplate.getForEntity(URL,Libro[].class);
			Libro[] arrayLibros = obtenerTodosLosLibros.getBody();
			return Arrays.asList(arrayLibros);//convertimos el array en un ArrayList
		} catch (HttpClientErrorException e) {
			System.out.println("obtenerTodosLosLibrosRs -> Error al obtener la lista de libros");
		    System.out.println("obtenerTodosLosLibrosRs -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	
	
	//Create
	public Libro altaLibro(Libro l) {
		listaLibros.add(l);
		return l;
		
	}
	
	//Create con restTemplate
	public Libro altaLibroRs(Libro libro){
		try {
			//Para hacer un post de una entidad usamos el metodo postForEntity
			//El primer parametro la URL
			//El segundo parametro la persona que ira en body
			//El tercer parametro el objeto que esperamos que nos envie el servidor
			ResponseEntity<Libro> re = restTemplate.postForEntity(URL, libro, Libro.class);
			System.out.println("altaLibroRs -> Codigo de respuesta " + re.getStatusCode());
			return re.getBody();
		} catch (HttpClientErrorException e) {//Errores 4XX
			System.out.println("altaLibroRs -> El libro NO se ha dado de alta, id: " + libro);
		    System.out.println("altaLibroRs -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	
	
	//Read
		public Libro obtenerLibroPorId(int id) {
	        for (Libro libro : listaLibros) {
	            if (libro.getId() == id) {
	                return libro;
	            }
	        }
	        return null; // No se encontró ninguna película con ese ID
	    }
	
	//Read con restTemplate
	public Libro obtenerLibroPorIdRs(int id){
		try {
			//Como el servicio trabaja con objetos ResponseEntity, nosotros 
			//tambien podemos hacerlo en el cliente
			//Ej http://localhost:8080/personas/1 GET
			ResponseEntity<Libro> re = restTemplate.getForEntity(URL + "/"+ id, Libro.class);
			HttpStatus hs = re.getStatusCode();
			if(hs == HttpStatus.OK) {	
				return re.getBody();
			}else {
				System.out.println("obtenerLibroPorIdRs -> Respuesta no contemplada");
				return null;
			}
		}catch (HttpClientErrorException e) {//Errores 4XX
			System.out.println("obtenerLibroPorIdRs -> El libro NO se ha encontrado, id: " + id);
		    System.out.println("obtenerLibroPorIdRs -> Codigo de respuesta: " + e.getStatusCode());
		    return null;
		}
	}
	
	
	//Update
	public Libro editarLibro(Libro libroNuevo) {
		for(Libro libro : listaLibros) {
			if(libroNuevo.getId() == libro.getId()) {
				libro.setId(libroNuevo.getId());
				libro.setTitulo(libroNuevo.getTitulo());
				libro.setEditorial(libroNuevo.getEditorial());
				libro.setNota(libroNuevo.getNota());
				return libroNuevo;
			}
		}
		return null;
		
	}
	
	//Update con restTemplate
	public boolean editarLibroRs(Libro libro){
		try {
			//El metodo put de Spring no devuelve nada
			//si no da error se ha dado de alta y si no daria una 
			//excepcion
			restTemplate.put(URL + "/" + libro.getId(), libro, Libro.class);
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("editarLibroRs -> El libro no se ha modificado, id: " + libro.getId());
		    System.out.println("editarLibroRs -> Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	
	
	//Delete
	public Libro eliminarLibroPorId(int id) {
        for (Libro libro : listaLibros) {
            if (libro.getId() == id) {
                listaLibros.remove(libro);
                return libro;
            }
        }
        return null; // No se encontró ninguna película con ese ID
    }
	
	//Delete con restTemplate
	public boolean eliminarLibroPorIdRs(int id){
		try {
			//El metodo delete tampoco devuelve nada, por lo que si no 
			//ha podido borrar el id, daría un excepcion
			//Ej http://localhost:8080/libros/1 DELETE
			restTemplate.delete(URL + "/" + id);
			System.out.println("Libro borrado correctamente!");
			return true;
		} catch (HttpClientErrorException e) {
			System.out.println("eliminarLibroPorIdRs -> El libro NO se ha borrado, id: " + id);
		    System.out.println("eliminarLibroPorIdRs -> Codigo de respuesta: " + e.getStatusCode());
		    return false;
		}
	}
	
	
	
	
	
}
