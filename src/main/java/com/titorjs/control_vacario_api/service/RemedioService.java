package com.titorjs.control_vacario_api.service;

import com.titorjs.control_vacario_api.entity.Enfermedad;
import com.titorjs.control_vacario_api.entity.Remedio;
import com.titorjs.control_vacario_api.repository.EnfermedadRepository;
import com.titorjs.control_vacario_api.repository.RemedioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RemedioService {

    @Autowired
    private RemedioRepository remedioRepository;

    @Autowired
    private EnfermedadRepository enfermedadRepository;

    public List<Remedio> getAllRemedios() {
        return remedioRepository.findAll();
    }

    public Remedio getRemedioById(Long id) {
        return remedioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Remedio no encontrado, id: " + id));
    }

    public Remedio createRemedio(Remedio remedio) {
        return remedioRepository.save(remedio);
    }

    public Remedio updateRemedio(Long id, Remedio remedioDetails) {
        Remedio remedio = getRemedioById(id);
        remedio.setRemedioName(remedioDetails.getRemedioName());
        remedio.setRemedioDesc(remedioDetails.getRemedioDesc());
        return remedioRepository.save(remedio);
    }

    public void deleteRemedio(Long id) {
        Remedio remedio = getRemedioById(id);
        Long remedios = enfermedadRepository.countEnfermedadesByRemedioId(id);
        if(remedios > 0){
            throw new RuntimeException("El remedio está asociado a enfermedades, no se puede eliminar");
        }
        remedioRepository.delete(remedio);
    }
}
