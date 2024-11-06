package com.titorjs.control_vacario_api.service;

import com.titorjs.control_vacario_api.entity.Enfermedad;
import com.titorjs.control_vacario_api.entity.Remedio;
import com.titorjs.control_vacario_api.repository.EnfermedadRepository;
import com.titorjs.control_vacario_api.repository.ProductoRepository;
import com.titorjs.control_vacario_api.repository.RemedioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnfermedadService {

    @Autowired
    private EnfermedadRepository enfermedadRepository;

    @Autowired
    private RemedioService remedioService;

    public List<Enfermedad> getAllEnfermedades() {
        return enfermedadRepository.findAll();
    }

    public Enfermedad getEnfermedadById(Long id) {
        return enfermedadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enfermedad not found with id: " + id));
    }

    public Enfermedad createEnfermedad(Enfermedad enfermedad) {
        return enfermedadRepository.save(enfermedad);
    }

    public Enfermedad updateEnfermedad(Long id, Enfermedad enfermedadDetails) {
        Enfermedad enfermedad = getEnfermedadById(id);
        enfermedad.setEnfermedadDesc(enfermedadDetails.getEnfermedadDesc());
        enfermedad.setEnfermedadInit(enfermedadDetails.getEnfermedadInit());
        enfermedad.setEnfermedadEnd(enfermedadDetails.getEnfermedadEnd());
        return enfermedadRepository.save(enfermedad);
    }

    public void deleteEnfermedad(Long id) {
        Enfermedad enfermedad = getEnfermedadById(id);
        enfermedadRepository.delete(enfermedad);
    }

    public Enfermedad addProduct(Long enfermedadId, Long productId){
        Enfermedad enfermedad = getEnfermedadById(enfermedadId);
        Remedio rm = remedioService.getRemedioById(productId);

        if(enfermedad.getRemedios().contains(rm)){
            throw new RuntimeException("La enfermedad ya contiene el producto designado");
        }

        enfermedad.getRemedios().add(rm);

        return enfermedad;
    }

    public void deleteProduct(Long enfermedadId, Long productId){
        Enfermedad enfermedad = getEnfermedadById(enfermedadId);
        Remedio rm = remedioService.getRemedioById(productId);

        if(!enfermedad.getRemedios().contains(rm)){
            throw new RuntimeException("La enfermedad no contiene el producto designado");
        }

        enfermedad.getRemedios().remove(rm);
    }
}
