package com.rest.alkemy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "peliculas")
public class Pelicula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private String imagen;
    private String titulo;
    private Date fecha_creacion;
    private int calificacion;

    @ManyToMany(mappedBy = "peliculas")
    @JsonIgnoreProperties({"peliculas"})
    private List<Personaje> personajes;

    @ManyToOne
    @JoinColumn(name = "genero_id")
    @JsonIgnoreProperties("listaPeliculas")
    private Genero genero;

}
