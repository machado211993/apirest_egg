package com.api.persona.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.api.persona.entidades.Persona;
import com.api.persona.servicios.PersonaServicio;

@RequestMapping("/personas")
@RestController
@CrossOrigin("*")
public class PersonaControlador {
    @Autowired
    private PersonaServicio ps;

    @GetMapping
    public ResponseEntity<List<Persona>> findAll() {
        try {
            List<Persona> personas = ps.findAll();
            if (personas == null || personas.isEmpty()) {
                return ResponseEntity.status(400).body(null);
            }
            return ResponseEntity.status(200).body(personas);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody Persona persona) {
        try {
            Persona p = ps.save(persona);
            return ResponseEntity.status(201).body(p);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/dni/{dni}")
    public ResponseEntity<Persona> findByDocumento(@PathVariable Long dni) {
        try {
            Persona persona = ps.findByDocumento(dni);
            return ResponseEntity.status(200).body(persona);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }
    /*@GetMapping("/search")
    public ResponseEntity<List<Persona>> findByDocumento(@RequestParam(required = false) String nombre, @RequestParam(required = false) Integer dni ) {
        try {
            List<Persona> personasFiltradas = ps.;
            return ResponseEntity.status(200).body(persona);
        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<Persona> findById(@PathVariable Integer id) {
        return ps.findById(id)
            .map(persona -> ResponseEntity.ok(persona))
            .orElseGet(() -> ResponseEntity.status(404).body(null));
    }
    

    @PutMapping("{id}")
    public ResponseEntity<Object> update (@PathVariable Integer id, @RequestBody Persona persona){
        try {
            Persona p = ps.update(id, persona);
            return ResponseEntity.status(201).body(p);

        } catch (Exception e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        ps.delete(id);
    }

}
