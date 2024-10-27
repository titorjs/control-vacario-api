package com.titorjs.control_vacario_api.service;

import com.titorjs.control_vacario_api.entity.Vaca;
import com.titorjs.control_vacario_api.entity.VacaType;
import com.titorjs.control_vacario_api.entity.VacaStatus;
import com.titorjs.control_vacario_api.repository.VacaRepository;
import com.titorjs.control_vacario_api.repository.VacaTypeRepository;
import com.titorjs.control_vacario_api.repository.VacaStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VacaService {

    @Autowired
    private VacaRepository vacaRepository;

    @Autowired
    private VacaTypeRepository vacaTypeRepository;

    @Autowired
    private VacaStatusRepository vacaStatusRepository;

    public List<Vaca> getAllVacas() {
        return vacaRepository.findAll();
    }

    public Vaca getVacaById(Long id) {
        return vacaRepository.findById(id).orElseThrow(() -> new RuntimeException("Vaca no encontrada"));
    }

    public Vaca createVaca(Vaca vaca, Long typeId, Long statusId) {
        VacaType vacaType = vacaTypeRepository.findById(typeId)
                .orElseThrow(() -> new RuntimeException("Tipo de vaca no encontrado"));
        VacaStatus vacaStatus = vacaStatusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Estado de vaca no encontrado"));

        vaca.setType(vacaType);
        vaca.setStatus(vacaStatus);

        return vacaRepository.save(vaca);
    }

    public Vaca updateVaca(Long id, Vaca vacaDetails, Long typeId, Long statusId) {
        Vaca vaca = vacaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaca no encontrada"));

        VacaType vacaType = vacaTypeRepository.findById(typeId)
                .orElseThrow(() -> new RuntimeException("Tipo de vaca no encontrado"));
        VacaStatus vacaStatus = vacaStatusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Estado de vaca no encontrado"));

        vaca.setCode(vacaDetails.getCode());
        vaca.setName(vacaDetails.getName());
        vaca.setDescription(vacaDetails.getDescription());
        vaca.setType(vacaType);
        vaca.setStatus(vacaStatus);

        return vacaRepository.save(vaca);
    }

    public void deleteVaca(Long id) {
        Vaca vaca = vacaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaca no encontrada"));
        vacaRepository.delete(vaca);
    }

    public Vaca sellVaca(Long id) {
        Vaca vaca = vacaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaca no encontrada"));

        vaca.setStatus(vacaStatusRepository.findByDescription("VENDIDA")
                .orElseThrow(() -> new RuntimeException("Estatus de VENDIDA no registrado en el sistema")));

        return vaca;
    }
}
