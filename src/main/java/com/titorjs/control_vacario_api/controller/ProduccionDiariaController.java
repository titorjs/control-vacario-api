package com.titorjs.control_vacario_api.controller;

import com.titorjs.control_vacario_api.entity.ProduccionDiaria;
import com.titorjs.control_vacario_api.repository.ProduccionDiariaRepository;
import com.titorjs.control_vacario_api.service.ProduccionDiariaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import jakarta.validation.constraints.NotNull;

@RestController
@RequestMapping("/api/${api.version}/produccion-diaria")
public class ProduccionDiariaController {

    @Autowired
    private ProduccionDiariaService produccionDiariaService;

    @Autowired
    private ProduccionDiariaRepository produccionDiariaRepository;

    @GetMapping
    public List<ProduccionDiaria> getAllProduccionDiaria() {
        return produccionDiariaService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> createProduccionDiaria(@RequestBody ProduccionDiariaRequest produccionDiaria) {
        try{
            return ResponseEntity.ok(
                    produccionDiariaService.add(produccionDiaria.getProduccionDate(),
                                                produccionDiaria.getVacaId(),
                                                produccionDiaria.getLiters()));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProduccionDiariaById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(produccionDiariaService.getById(id));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduccionDiaria(@PathVariable Long id, @RequestBody ProduccionDiariaRequest produccionDiariaDetails) {
        try{
            ProduccionDiaria produccionDiaria = produccionDiariaService.getById(id);

            produccionDiaria.getId().setProduccionDate(produccionDiariaDetails.getProduccionDate());
            produccionDiaria.setProduccionLiters(produccionDiariaDetails.getLiters());

            ProduccionDiaria updatedProduccionDiaria = produccionDiariaRepository.save(produccionDiaria);
            return ResponseEntity.ok(updatedProduccionDiaria);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduccionDiaria(@PathVariable Long id) {
        try{
            ProduccionDiaria pd = produccionDiariaService.getById(id);

            produccionDiariaRepository.delete(pd);

            return ResponseEntity.ok().build();
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

@Data
class ProduccionDiariaRequest {
    @NotNull(message = "La fecha de producción no puede ser nula")
    private LocalDate produccionDate;

    @NotNull(message = "El ID de la vaca no puede ser nulo")
    private Long vacaId;

    @NotNull(message = "La cantidad de litros producidos no puede ser nula")
    private Double liters;
}