package com.example.tp2.service;

import com.example.tp2.entities.Bloc;
import com.example.tp2.entities.Foyer;
import com.example.tp2.entities.Universite;
import com.example.tp2.repository.BlocRepository;
import com.example.tp2.repository.FoyerRepository;
import com.example.tp2.repository.UniversiteRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tp2.repository.UniversiteRepository;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UniversiteServiceImpl  implements IUniversiteService{

    @Autowired
    UniversiteRepository universiteRepository;
    FoyerRepository foyerRepository;
    BlocRepository blocRepository;



    @Transactional
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


    @Override
    public Universite addUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversite(Universite u) {
        return universiteRepository.save(u);
    }

    @Override
    public void deleteUniversite(Long universiteId) {
        universiteRepository.deleteById(universiteId);

    }
    @Override
    public Universite getUniversiteById(Long universiteId) {
        Optional<Universite> universiteOptional = universiteRepository.findById(universiteId);
        return universiteOptional.orElseThrow(() -> new EntityNotFoundException("Bloc not found with ID: " + universiteId));
    }

    @Override
    public List<Universite> getAllUniversites() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite desaffecterFoyerAUniversite(long idUniversite) {

        Universite universite =
                universiteRepository.findById(idUniversite).orElse(null);
        universite.setFoyer(null);
        return universiteRepository.save(universite);
    }

    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        Foyer foyer = foyerRepository.findById(idFoyer).orElse(null);
        Universite universite =
                universiteRepository.findUniversiteByNom(nomUniversite);

        universite.setFoyer(foyer);
        return universiteRepository.save(universite);
    }
}
