package com.rest.alkemy.controller;

import com.rest.alkemy.entity.Personaje;
import com.rest.alkemy.service.PersonajeService;
import com.rest.alkemy.utils.JWTUtil;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/characters")
public class PersonajeController {

    @Autowired private PersonajeService personajeService;
    @Autowired private JWTUtil jwtUtil;

    //    /v3/api-docs

   

    @Operation(summary = "almacena en la DB una instancia de personaje", description = "")
    @PostMapping("")
    public ResponseEntity<Personaje> savePersonaje(@RequestBody Personaje personaje){
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeService.savePersonaje(personaje));
    }

    @Operation(summary = "actualiza campos del personaje", description = "")
    @PutMapping("")
    public ResponseEntity<Personaje> updatePersonaje(@RequestBody Personaje personaje){
        return ResponseEntity.status(HttpStatus.CREATED).body(personajeService.updatePersonaje(personaje));
    }

    @Operation(summary = "elimina un personaje por su id, Requiere un token", description = "debe tener un token"
    		+ " valido para ejecutar la acción, se envia en el header, como atributo del "
    		+ "campo token")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePersonaje(
    		@PathVariable("id") int id,
    		@RequestHeader(value = "token") String token
    		){
    	String userId = jwtUtil.getKey(token);
        personajeService.deletePersonaje(id);
    }

    @Operation(summary = "retorna un personaje por su id", description = "")
    @GetMapping("/{id}")
    public ResponseEntity<Personaje> getPersonaje(@PathVariable("id") int id){
        return ResponseEntity.ok(personajeService.getPersonaje(id).get());
    }

    @Operation(summary = "retorna una lista con todos los personajes, adicionalmente filtra de acuerdo a su nombre o edad",description = "")
    @GetMapping("")
    public ResponseEntity<List<Personaje>> filtroPersonaje(
            @RequestParam(required = false, defaultValue = "") String nombre,
            @RequestParam(required = false, defaultValue = "0") int edad            
    ){
        return ResponseEntity.ok(personajeService.filterPersonaje(nombre, edad));
    }

}
