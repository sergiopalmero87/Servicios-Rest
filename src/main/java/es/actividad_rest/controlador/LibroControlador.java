package es.actividad_rest.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.actividad_rest.modelo.entidad.Libro;
import es.actividad_rest.modelo.negocio.LibroService;

@RestController
public class LibroControlador {
	
	@Autowired
	LibroService libroService;
	
	
	//Obtener libro por ID
	@GetMapping(path="libros/{id}",produces = MediaType.APPLICATION_JSON_VALUE)	
	public ResponseEntity<Libro> obtenerLibroPorId(@PathVariable("id") int id) {
		System.out.println("Buscando libro con id: " + id);
		Libro libro = libroService.obtenerLibroPorId(id);
		if(libro != null) {
			return new ResponseEntity<Libro>(libro,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//Obtener todos los libros
	@GetMapping(path="libros",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Libro>> obtenerTodosLosLibros(){
		List<Libro> listaLibros;
		System.out.println("Listando todos los libros");
		listaLibros = libroService.obtenerTodosLosLibros();			
		return new ResponseEntity<List<Libro>>(listaLibros,HttpStatus.OK);
		}
	
	//Borrar un libro segun su id.
	@DeleteMapping(path="libros/{id}")
	public ResponseEntity<Libro> eliminarLibroPorId(@PathVariable("id") int id) {
		System.out.println("Id del libro a borrar: " + id);
		Libro libro = libroService.eliminarLibroPorId(id);
		System.out.println("Libro eliminado: " + libro);
		if(libro != null) {
			return new ResponseEntity<Libro>(libro,HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
	//Dar de alta un libro
	@PostMapping(path="libros",
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> altaLibro(@RequestBody Libro libro) {
		System.out.println("altaLibro: objeto libro: " + libro);
		libroService.altaLibro(libro);
		return new ResponseEntity<Libro>(libro, HttpStatus.CREATED);//201 CREATED
	}
	
	//Modificar libro segun su id
	@PutMapping(path="libros/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Libro> editarLibro(@PathVariable("id") int id, @RequestBody Libro libro) {
		System.out.println("libro a modificar segun su id: " + id);
		System.out.println("datos modificados: " + libro);
		Libro libroUpdate = libroService.editarLibro(libro);
		if(libroUpdate != null) {
			return new ResponseEntity<Libro>(libroUpdate, HttpStatus.OK);//200 OK
		}else {
			return new ResponseEntity<Libro>(HttpStatus.NOT_FOUND);//404 NOT FOUND
		}
	}
	
}
		
	


