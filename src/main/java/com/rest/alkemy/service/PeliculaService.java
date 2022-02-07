package com.rest.alkemy.service;

import com.rest.alkemy.entity.Pelicula;
import com.rest.alkemy.repository.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaService {

    @Autowired private PeliculaRepository peliculaRepository;

    public Optional<Pelicula> getPelicula(int id){
        return peliculaRepository.findById(id);
    }

    public List<Pelicula> getPeliculas(){
        return peliculaRepository.findAll();
    }

    public Pelicula savePelicula(Pelicula pelicula){
        return peliculaRepository.save(pelicula);
    }

    public void deletePelicula(int id){
        peliculaRepository.deleteById(id);
    }

    public Pelicula updatePelicula(Pelicula pelicula) {
        Optional<Pelicula> oPelicula = peliculaRepository.findById(pelicula.getId());
        oPelicula.get().setImagen(pelicula.getImagen());
        oPelicula.get().setTitulo(pelicula.getTitulo());
        oPelicula.get().setFecha_creacion(pelicula.getFecha_creacion());
        oPelicula.get().setCalificacion(pelicula.getCalificacion());
        return peliculaRepository.save(oPelicula.get());
    }

    public List<Pelicula> filterPelicula(String titulo, int idGenero, String order) {
    	
        if(!"".equals(titulo) && idGenero == 0 && "".equals(order)){
            return peliculaRepository.findByTitulo(titulo);
        }
        
        if("".equals(titulo) && idGenero > 0 && "".equals(order)){
            return peliculaRepository.findByGeneroId(idGenero);
        }
        
        if("".equals(titulo) && idGenero == 0 && !"".equals(order)){
        	if("ASC".equals(order)) {
        		return peliculaRepository.findAllOrderByTituloAsc();
        	}
        	return peliculaRepository.findAllOrderByTituloDesc();
        	
        }
        
        if(!"".equals(titulo) && idGenero > 0 && "".equals(order)){
            return peliculaRepository.findByTituloAndIdGenero(titulo, idGenero);
        }
        
        if("".equals(titulo) && idGenero > 0 && !"".equals(order)){
        	if("ASC".equals(order)) {
				return peliculaRepository.findByIdGeneroOrderByTituloAsc(idGenero);
			}
			return peliculaRepository.findByIdGeneroOrderByTituloDesc(idGenero);
        }
        
        if(!"".equals(titulo) && idGenero == 0 && !"".equals(order)){
        	if("ASC".equals(order)) {
        		return peliculaRepository.findByTituloOrderByTituloAsc(titulo);
        	}
            return peliculaRepository.findByTituloOrderBytituloDesc(titulo);
        }
        
        if(!"".equals(titulo) && idGenero > 0 && !"".equals(order)){
        	if("ASC".equals(order)) {
        		return peliculaRepository.findByIdGeneroAndTituloOrderByTituloAsc(titulo, idGenero);
        	}
        	return peliculaRepository.findByIdGeneroAndTituloOrderByTituloDesc(titulo, idGenero);
        }       
        

        return peliculaRepository.findAll();
    }
}
