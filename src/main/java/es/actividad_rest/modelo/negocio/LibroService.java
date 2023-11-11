package es.actividad_rest.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.actividad_rest.modelo.entidad.Libro;

@Service
public class LibroService {

	private List<Libro> listaLibros;

	public LibroService() {
		System.out.println("Logica -> Creando la lista de libros!");
		listaLibros = new ArrayList<Libro>();
		Libro l1 = new Libro(1, "Libro 1", "Editorial 1", 10.0);
		Libro l2 = new Libro(2, "Libro 2", "Editorial 2", 12.0);
		Libro l3 = new Libro(3, "Libro 3", "Editorial 3", 14.0);
		Libro l4 = new Libro(4, "Libro 4", "Editorial 4", 16.0);
		Libro l5 = new Libro(5, "Libro 5", "Editorial 5", 18.0);
		listaLibros.add(l1);
		listaLibros.add(l2);
		listaLibros.add(l3);
		listaLibros.add(l4);
		listaLibros.add(l5);
	}
	
	//Metodos CRUD.
	//Obtener todos los libros.
	public List<Libro> obtenerTodosLosLibros() {
        return listaLibros;
    }
	
	
	//Create
	public List<Libro> altaLibro(Libro l) {
		for (Libro libro : listaLibros) {
			if(libro.getId() == l.getId() || libro.getTitulo().equalsIgnoreCase(l.getTitulo())) {
				return null;
			} else {
				listaLibros.add(l);
			}
		}
		return listaLibros;
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
	
	
	//Update
	public Libro editarLibro(Libro libroActualizado) {
		for(Libro libro : listaLibros) {
			if(libro.getId() == libroActualizado.getId()) {
				libro.setTitulo(libroActualizado.getTitulo());
				libro.setEditorial(libroActualizado.getEditorial());
				libro.setNota(libroActualizado.getNota());
				return libro;
			}
			
		}
		return null;
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
	
	
	
	
	
}
