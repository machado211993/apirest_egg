package com.api.persona.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.persona.entidades.Persona;

@Repository
public interface PersonaRepositorio extends JpaRepository<Persona, Integer> {
    
    @Query("SELECT p FROM Persona p WHERE p.documento = :dni")
    public Persona findByDocumento(Long dni);
    /*@Query("SELECT p FROM Persona p WHERE (:nombre IS NULL OR p.nombre LIKE %:nombre%) AND(:edad IS NULL OR p.edad = :edad)")*/
    public List<Persona> findAllByNombreOrEdad(String nombre, Integer edad);

}
