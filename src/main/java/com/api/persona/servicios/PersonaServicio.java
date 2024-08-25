package com.api.persona.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.persona.entidades.Persona;
import com.api.persona.repositorios.PersonaRepositorio;

@Service
public class PersonaServicio {

    @Autowired
    private PersonaRepositorio pr;

    public Persona save(Persona persona) throws Exception {
        validate(persona);
        Persona p = pr.findByDocumento(persona.getDocumento());
        if (p != null) {
            throw new Exception("Documento existente");

        }
        return pr.save(persona);

    }

    public Persona update(Integer id, Persona persona) throws Exception {
        validate(persona);
        Persona p = pr.getById(id);
        p.setEdad(persona.getEdad());
        p.setNombre(persona.getNombre());
        return pr.save(p);
    }

    public void delete(Integer id) {
        pr.deleteById(id);
    }

    public Persona findByDocumento(Long dni) {
        return pr.findByDocumento(dni);
    }

    public List<Persona> findAll() {
        return pr.findAll();
    }

    public Optional<Persona> findById(Integer id) {
        return pr.findById(id);
    }
    

    private void validate(Persona persona) throws Exception {
        if (persona.getNombre().isEmpty() || persona.getNombre() == null) {
            throw new Exception("Nombre invalido");
        }
        if (persona.getEdad().isEmpty() || persona.getEdad() == null) {
            throw new Exception("Edad invalida");
        }
        if (persona.getDocumento() == null || persona.getDocumento() < 1) {
            throw new Exception("Documento invalido");
        }
    }
}
