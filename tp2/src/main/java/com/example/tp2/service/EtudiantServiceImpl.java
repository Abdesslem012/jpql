package com.example.tp2.service;

import com.example.tp2.entities.Etudiant;
import com.example.tp2.repository.EtudiantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EtudiantServiceImpl implements IEtudiantService{

    @Autowired
    EtudiantRepository etudiantRepository;

    @Override
    public Etudiant addEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        return etudiantRepository.save(e);
    }

    @Override
    public void deleteEtudiant(Long etudiantId) {
        etudiantRepository.deleteById(etudiantId);

    }

    @Override
    public Etudiant getEtudiantById(Long etudiantId) {
        Optional<Etudiant> etudiantOptional = etudiantRepository.findById(etudiantId);
        return etudiantOptional.orElseThrow(() -> new EntityNotFoundException("Etudiant not found with ID: " + etudiantId));
    }

    @Override
    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }
}
