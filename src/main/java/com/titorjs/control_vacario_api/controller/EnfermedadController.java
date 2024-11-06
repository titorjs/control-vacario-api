package com.titorjs.control_vacario_api.controller;

import com.titorjs.control_vacario_api.entity.Enfermedad;
import com.titorjs.control_vacario_api.service.EnfermedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/enfermedades")
public class EnfermedadController {

    @Autowired
    private EnfermedadService enfermedadService;

    @GetMapping
    public ResponseEntity<List<Enfermedad>> getAllEnfermedades() {
        try {
            return ResponseEntity.ok(enfermedadService.getAllEnfermedades());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getEnfermedadById(@PathVariable Long id) {
        try {
            Enfermedad enfermedad = enfermedadService.getEnfermedadById(id);
            return ResponseEntity.ok(enfermedad);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createEnfermedad(@RequestBody Enfermedad enfermedad) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(enfermedadService.createEnfermedad(enfermedad));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEnfermedad(@PathVariable Long id, @RequestBody Enfermedad enfermedadDetails) {
        try {
            Enfermedad updatedEnfermedad = enfermedadService.updateEnfermedad(id, enfermedadDetails);
            return ResponseEntity.ok(updatedEnfermedad);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEnfermedad(@PathVariable Long id) {
        try {
            enfermedadService.deleteEnfermedad(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/addRemedio/{id}/{remedio}")
    public ResponseEntity<?> addRemedio(@PathVariable Long id, @PathVariable Long remedio){
        try{
            return ResponseEntity.ok().body(enfermedadService.addProduct(id, remedio));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
