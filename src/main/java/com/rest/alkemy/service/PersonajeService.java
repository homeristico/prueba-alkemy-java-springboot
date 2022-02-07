package com.rest.alkemy.service;

import com.rest.alkemy.entity.Personaje;
import com.rest.alkemy.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService {

    @Autowired private PersonajeRepository personajeRepository;

    public Optional<Personaje> getPersonaje(int id){
        return personajeRepository.findById(id);
    }

    public List<Personaje> getPersonajes(){
        return personajeRepository.findAll();
    }

    public Personaje savePersonaje(Personaje personaje){
        Optional<Personaje> optionalPersonaje = personajeRepository.findById(personaje.getId());
        if(optionalPersonaje.isPresent()){
            return personajeRepository.save(personaje);
        }
        Personaje newPersonaje = new Personaje();
        newPersonaje.setImagen(personaje.getImagen());
        newPersonaje.setNombre(personaje.getNombre());
        newPersonaje.setEdad(personaje.getEdad());
        newPersonaje.setPeso(personaje.getPeso());
        newPersonaje.setHistoria(personaje.getHistoria());

        Personaje personajeConId = personajeRepository.save(newPersonaje);
        personaje.setId(personajeConId.getId());
        return personajeRepository.save(personaje);
    }

    public void deletePersonaje(int id){
        personajeRepository.deleteById(id);
    }



    public Personaje updatePersonaje(Personaje personaje) {
        Optional<Personaje> oPersonaje = personajeRepository.findById(personaje.getId());
        oPersonaje.get().setImagen(personaje.getImagen());
        oPersonaje.get().setNombre(personaje.getNombre());
        oPersonaje.get().setEdad(personaje.getEdad());
        oPersonaje.get().setPeso(personaje.getPeso());
        oPersonaje.get().setHistoria(personaje.getHistoria());
        return personajeRepository.save(oPersonaje.get());
    }
    
   

    public List<Personaje> filterPersonaje(String nombre, int edad) {
        if (!"".equals(nombre) && edad == 0){
            return personajeRepository.findByNombre(nombre);
        }
        if ("".equals(nombre) && edad > 0){
            return personajeRepository.findByEdad(edad);
        }       
        if (!"".equals(nombre) && edad > 0){
            return personajeRepository.findByNombreAndEdad(nombre, edad);
        } 
        
        return personajeRepository.findAll();

    }
}
