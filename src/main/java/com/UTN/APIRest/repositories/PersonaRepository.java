package com.UTN.APIRest.repositories;

import com.UTN.APIRest.entities.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends BaseRepository <Persona, Long> {

    List<Persona>findByNombreContainingOrApellidoContaining(String nombre, String apellido);
    List<Persona>findByNombreContainingOrApellidoContaining(String nombre, String apellido, Pageable pageable);


    boolean existsByDni(int dni);


    @Query(value = "SELECT p FROM Persona p WHERE p.nombre LIKE '%?1%' OR p.apellido LIKE '%?1%'")
    Page<Persona> search(String filtro);

    @Query(value = "SELECT p FROM Persona p WHERE p.nombre LIKE '%?1%' OR p.apellido LIKE '%?1%'")
    Page<Persona> search(String filtro, Pageable pageable);

    @Query(
            value = "SELECT * FROM persona WHERE persona.nombre LIKE '%?1%' OR persona.apellido LIKE '%?1%'",
            nativeQuery = true
    )
    List<Persona> search1(String filtro);

    @Query(
            value = "SELECT * FROM persona WHERE persona.nombre LIKE '%?1%' OR persona.apellido LIKE '%?1%'",
            countQuery = "SELECT count(*) FROM persona ",
            nativeQuery = true
    )
    Page<Persona> search1(String filtro, Pageable pageable);
}