package com.rest.alkemy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "personajes")
public class Personaje implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2289144489450195249L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String imagen;
    private String nombre;
    private int edad;
    private float peso;
    private String historia;

    @JoinTable(
            name = "personaje_pelicula",
            joinColumns = @JoinColumn(name = "personaje_id", nullable = false),
            inverseJoinColumns = @JoinColumn(name="pelicula_id", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"personajes"})
    private List<Pelicula> peliculas;




















}
