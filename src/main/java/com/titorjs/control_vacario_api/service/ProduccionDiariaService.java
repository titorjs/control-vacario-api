package com.titorjs.control_vacario_api.service;

import com.titorjs.control_vacario_api.entity.ProduccionDiaria;
import com.titorjs.control_vacario_api.entity.Vaca;
import com.titorjs.control_vacario_api.repository.ProduccionDiariaRepository;
import com.titorjs.control_vacario_api.repository.VacaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProduccionDiariaService {

    @Autowired
    private ProduccionDiariaRepository produccionDiariaRepository;

    @Autowired
    private VacaRepository vacaRepository;

    public List<ProduccionDiaria> getAll(){
        return produccionDiariaRepository.findAll();
    }

    public ProduccionDiaria getById(Long id) {
        return produccionDiariaRepository.findById(id).orElseThrow(() -> new RuntimeException("Producción Diaria no encontrada"));
    }

    public ProduccionDiaria add(LocalDate produccionDate, Long vacaId, Double liters) {
        Vaca vaca = vacaRepository.findById(vacaId)
                .orElseThrow(() -> new RuntimeException("Vaca no encontrada con ID: " + vacaId));

        ProduccionDiaria.ProduccionDiarioId produccionDiarioId = new ProduccionDiaria.ProduccionDiarioId();
        produccionDiarioId.setVaca(vaca);
        produccionDiarioId.setProduccionDate(produccionDate);

        ProduccionDiaria produccionDiaria = new ProduccionDiaria();
        produccionDiaria.setId(produccionDiarioId);
        produccionDiaria.setProduccionLiters(liters);

        return produccionDiaria;
    }
}
