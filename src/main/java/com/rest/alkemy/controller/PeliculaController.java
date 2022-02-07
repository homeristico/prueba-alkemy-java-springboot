package com.rest.alkemy.controller;

import com.rest.alkemy.entity.Pelicula;
import com.rest.alkemy.service.PeliculaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class PeliculaController {

    @Autowired private PeliculaService peliculaService;

  
    @Operation(summary = "almacena una instancia de Pelicula en la DB", description = "")
    @PostMapping("")
    public ResponseEntity<Pelicula> savePelicula(@RequestBody Pelicula pelicula){
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaService.savePelicula(pelicula));
    }

    @Operation(summary = "actualiza una pelicula por id", description = "")
    @PutMapping("")
    public ResponseEntity<Pelicula> updatePelicula(@RequestBody Pelicula pelicula){
        return ResponseEntity.status(HttpStatus.CREATED).body(peliculaService.updatePelicula(pelicula));
    }

    @Operation(summary = "elimina una pelicula por su id")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePelicula(@PathVariable("id") int id){
        peliculaService.deletePelicula(id);
    }

    @Operation(summary = "obtiene una pelicula por id")
    @GetMapping("/{id}")
    public ResponseEntity<Pelicula> getPelicula(@PathVariable("id") int id){
        return ResponseEntity.ok(peliculaService.getPelicula(id).get());
    }

    @Operation(summary = "returna una lista con todas las peliculas", description = "")
    @GetMapping("")
    public List<Pelicula> filtroPelicula(
            @RequestParam(required = false, defaultValue = "") String titulo,
            @RequestParam(required = false, defaultValue = "0") int idGenero,
            @RequestParam(required = false,defaultValue = "") String order
    ){
        return peliculaService.filterPelicula(titulo, idGenero, order);
    }


}
