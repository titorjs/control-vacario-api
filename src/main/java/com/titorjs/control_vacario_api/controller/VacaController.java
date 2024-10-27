package com.titorjs.control_vacario_api.controller;

import com.titorjs.control_vacario_api.entity.Vaca;
import com.titorjs.control_vacario_api.service.VacaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/${api.version}/vacas")
public class VacaController {

    @Autowired
    private VacaService vacaService;

    @GetMapping
    public List<Vaca> getAllVacas() {
        return vacaService.getAllVacas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vaca> getVacaById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(vacaService.getVacaById(id));
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Vaca> createVaca(@RequestBody VacaRequest request) {
        Vaca vaca = new Vaca(request.getCode(), request.getName(), request.getDescription(), null, null);
        return ResponseEntity.ok(vacaService.createVaca(vaca, request.getTypeId(), request.getStatusId()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vaca> updateVaca(@PathVariable Long id, @RequestBody VacaRequest request) {
        Vaca vaca = new Vaca(request.getCode(), request.getName(), request.getDescription(), null, null);
        return ResponseEntity.ok(vacaService.updateVaca(id, vaca, request.getTypeId(), request.getStatusId()));
    }

    @PutMapping("/sell/{id}")
    public ResponseEntity<?> sellVaca(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(vacaService.sellVaca(id));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVaca(@PathVariable Long id) {
        try{
            vacaService.deleteVaca(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

@Data
class VacaRequest {
    private String code;
    private String name;
    private String description;
    private Long typeId;
    private Long statusId;
}
