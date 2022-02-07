package com.rest.alkemy.service;

import com.rest.alkemy.entity.Genero;
import com.rest.alkemy.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

    @Autowired private GeneroRepository generoRepository;

    public Genero getGenero(int id){
        return generoRepository.getById(id);
    }

    public List<Genero> getGeneros(){
        return generoRepository.findAll();
    }

    public Genero saveGenero(Genero genero){
        return generoRepository.save(genero);
    }

    public void deleteGenero(int id){
        generoRepository.deleteById(id);
    }

    public Genero updatePersonaje(Genero genero) {
        Optional<Genero> oGenero = generoRepository.findById(genero.getGenero_id());
        oGenero.get().setNombre(genero.getNombre());
        oGenero.get().setImagen(genero.getImagen());
        return generoRepository.save(oGenero.get());
    }

}
