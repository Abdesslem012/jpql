package com.example.tp2.service;

import com.example.tp2.entities.Bloc;
import com.example.tp2.entities.Foyer;
import com.example.tp2.entities.Universite;
import com.example.tp2.repository.BlocRepository;
import com.example.tp2.repository.FoyerRepository;
import com.example.tp2.repository.UniversiteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FoyerServiceImpl implements IFoyerService{

    @Autowired
    FoyerRepository foyerRepository;
    @Autowired
    UniversiteRepository universiteRepository;
    @Autowired
    BlocRepository blocRepository;

    @Override
    public Foyer addFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public Foyer updateFoyer(Foyer f) {
        return foyerRepository.save(f);
    }

    @Override
    public void deleteFoyer(Long foyerId) {
        foyerRepository.deleteById(foyerId);

    }

    @Override
    public Foyer getFoyerById(Long foyerId) {
        Optional<Foyer> foyerOptional = foyerRepository.findById(foyerId);
        return foyerOptional.orElseThrow(() -> new EntityNotFoundException("Foyer not found with ID: " + foyerId));
    }

    @Override
    public List<Foyer> getAllFoyers() {
        return foyerRepository.findAll();
    }

    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        Universite universite = universiteRepository.findById(idUniversite).orElse(null);
        foyerRepository.save(foyer);

        for(Bloc bloc : foyer.getBlocs())
        {
            bloc.setFoyer(foyer);
            blocRepository.save(bloc);
        }

        assert universite != null;
        universite.setFoyer(foyer);
        universiteRepository.save(universite);
        return foyer;
    }

}
