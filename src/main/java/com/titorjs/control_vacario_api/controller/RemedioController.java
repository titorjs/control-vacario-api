package com.titorjs.control_vacario_api.controller;

import com.titorjs.control_vacario_api.entity.Remedio;
import com.titorjs.control_vacario_api.service.RemedioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/remedios")
public class RemedioController {

    @Autowired
    private RemedioService remedioService;

    @GetMapping
    public List<Remedio> getAllRemedios() {
        return remedioService.getAllRemedios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRemedioById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(remedioService.getRemedioById(id));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createRemedio(@RequestBody Remedio remedio) {
        try{
            return ResponseEntity.ok(remedioService.createRemedio(remedio));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRemedio(@PathVariable Long id, @RequestBody Remedio remedioDetails) {
        try{
            return ResponseEntity.ok(remedioService.updateRemedio(id, remedioDetails));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRemedio(@PathVariable Long id) {
        try{
            remedioService.deleteRemedio(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
