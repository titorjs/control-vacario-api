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
        return ResponseEntity.ok(vacaService.getVacaById(id));
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVaca(@PathVariable Long id) {
        vacaService.deleteVaca(id);
        return ResponseEntity.noContent().build();
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
