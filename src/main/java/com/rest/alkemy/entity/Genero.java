package com.rest.alkemy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "generos")
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genero_id")
    private int genero_id;
    private String nombre;
    private String imagen;

    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "genero")
    @JsonIgnoreProperties("genero")
    private List<Pelicula> listaPeliculas;














}
