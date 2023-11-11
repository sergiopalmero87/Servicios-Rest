package es.actividad_rest.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import es.actividad_rest.modelo.entidad.Libro;

@Service
public class LibroService {

	public List<Libro> listaLibros;
	public int contador;

	public LibroService() {
		System.out.println("Logica -> Creando la lista de libros!");
		listaLibros = new ArrayList<Libro>();
		Libro l1 = new Libro(0, "Libro 1", "Editorial 1", 10.0);
		Libro l2 = new Libro(1, "Libro 2", "Editorial 2", 12.0);
		Libro l3 = new Libro(2, "Libro 3", "Editorial 3", 14.0);
		Libro l4 = new Libro(3, "Libro 4", "Editorial 4", 16.0);
		Libro l5 = new Libro(4, "Libro 5", "Editorial 5", 18.0);
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
		listaLibros.add(l);
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
	public Libro editarLibro(Libro libro) {
		
		try {
		Libro libroAux = listaLibros.get(libro.getId());
		libroAux.setTitulo(libro.getTitulo());
		libroAux.setEditorial(libro.getEditorial());
		libroAux.setNota(libro.getNota());

		return libroAux;
		
		} catch (IndexOutOfBoundsException iobe) {
			System.out.println("update -> Libro fuera de rango");
			return null;
		}
		
	}
	
	/*//Update 2
	public Libro editarLibro(int id) {
		try {
			for(Libro libro : listaLibros) {
				if(id == libro.getId()) {
					Libro libroAux = null;
					libro.setTitulo(libroAux.getTitulo());
					libro.setEditorial(libroAux.getEditorial());
					libro.setNota(libroAux.getNota());
					return libroAux;
				}
			}
			
		} catch (Exception e) {
			
		}
		return null;
	}*/
	
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
