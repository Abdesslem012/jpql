package com.example.tp2.service;

import com.example.tp2.entities.*;
import com.example.tp2.repository.BlocRepository;
import com.example.tp2.repository.ChambreRepository;
import com.example.tp2.repository.UniversiteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ChambreServiceImpl implements IChambreService{

    @Autowired
    ChambreRepository chambreRepository;
    @Autowired
    BlocRepository blocRepository;
    @Autowired
    UniversiteRepository universiteRepository;

    @Override
    public Chambre addChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public Chambre updateChambre(Chambre c) {
        return chambreRepository.save(c);
    }

    @Override
    public void deleteChambre(Long chambreId) {
        chambreRepository.deleteById(chambreId);
    }

    @Override
    public Chambre getChambreById(Long chambreId) {
        Optional<Chambre> chambreOptional = chambreRepository.findById(chambreId) ;
        return chambreOptional.orElseThrow(() -> new EntityNotFoundException("Chambre not found with ID: " + chambreId));
    }

    @Override
    public List<Chambre> getAllChambres() {
        return chambreRepository.findAll();
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {
        Bloc bloc = blocRepository.findById(idBloc).orElse(null);

        List<Chambre> chambreList = chambreRepository.findAllByNumeroChambreIn(numChambre);

        for(Chambre chambre: chambreList) {
            chambre.setBloc(bloc);
            chambreRepository.save(chambre);
        }
        return bloc;
    }

    @Override
    public List<Chambre> getChambresByNomUniversite(String nomUniversite) {
        List<Chambre> chambres = new ArrayList<>();
        List<Bloc> blocs = new ArrayList<>();

        // Fetch Universite by name
        Universite universite = universiteRepository.findUniversiteByNom(nomUniversite);

        if (universite != null) {
            // Fetch Foyer associated with Universite
            Foyer foyer = universite.getFoyer();

            if (foyer != null) {
                // Get Blocs associated with Foyer
                blocs.addAll(foyer.getBlocs());

                // Iterate through each Bloc and get associated Chambres
                for (Bloc bloc : blocs) {
                    chambres.addAll(chambreRepository.findChambresByBloc(bloc));
                }
            }
        }

        return chambres;

    }

    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        return chambreRepository.findChambresByBlocIdAndType(idBloc, typeC);
    }

    @Override
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type) {
        return chambreRepository.findUnreservedRoomsByDormitoryAndRoomType(nomUniversite, type);
    }


}

