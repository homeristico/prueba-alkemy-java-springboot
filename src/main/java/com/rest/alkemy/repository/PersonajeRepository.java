package com.rest.alkemy.repository;

import com.rest.alkemy.entity.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Integer> {

    public List<Personaje> findByNombre(String nombre);

    public List<Personaje> findByEdad(int edad);

    public List<Personaje> findByPeliculas(int movies);

    public List<Personaje> findByNombreAndEdad(String nombre, int edad);
  

  

  

 
    
    
    
    
    
    
    
}
