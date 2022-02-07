package com.rest.alkemy.repository;

import com.rest.alkemy.entity.Pelicula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepository extends JpaRepository<Pelicula, Integer> {


    List<Pelicula> findByTitulo(String titulo);

    @Query(value = "SELECT * FROM peliculas WHERE genero_id = ?1", nativeQuery = true)
	List<Pelicula> findByGeneroId(int idGenero);

    @Query(value = "SELECT * FROM peliculas WHERE titulo = ? and genero_id = ?", nativeQuery = true)
	List<Pelicula> findByTituloAndIdGenero(String titulo, int genero_id);

	@Query(value = "SELECT * FROM peliculas ORDER BY titulo DESC", nativeQuery = true)
	List<Pelicula> findAllOrderByTituloDesc();

	@Query(value = "SELECT * FROM peliculas ORDER BY titulo ASC", nativeQuery = true)
	List<Pelicula> findAllOrderByTituloAsc();
	
	@Query(value = "SELECT * FROM peliculas WHERE titulo = ?1 ORDER BY titulo ASC", nativeQuery = true)
	List<Pelicula> findByTituloOrderByTituloAsc(String titulo);

	@Query(value = "SELECT * FROM peliculas WHERE titulo = ?1 ORDER BY titulo DESC", nativeQuery = true)
	List<Pelicula> findByTituloOrderBytituloDesc(String titulo);

	@Query(value = "SELECT * FROM peliculas WHERE genero_id = ?1 ORDER BY titulo ASC", nativeQuery = true)
	List<Pelicula> findByIdGeneroOrderByTituloAsc(int generoId);

	@Query(value = "SELECT * FROM peliculas WHERE genero_id = ?1 ORDER BY titulo DESC", nativeQuery = true)
	List<Pelicula> findByIdGeneroOrderByTituloDesc(int generoId);

	@Query(value = "SELECT * FROM peliculas WHERE titulo = ? AND genero_id = ? ORDER BY titulo ASC", nativeQuery = true)
	List<Pelicula> findByIdGeneroAndTituloOrderByTituloAsc(String titulo, int idGenero);

	@Query(value = "SELECT * FROM peliculas WHERE titulo = ? AND genero_id = ? ORDER BY titulo DESC", nativeQuery = true)
	List<Pelicula> findByIdGeneroAndTituloOrderByTituloDesc(String titulo, int idGenero);




}
