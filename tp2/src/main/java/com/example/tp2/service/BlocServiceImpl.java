package com.example.tp2.service;

import com.example.tp2.entities.Bloc;
import com.example.tp2.entities.Chambre;
import com.example.tp2.entities.Foyer;
import com.example.tp2.entities.Universite;
import com.example.tp2.repository.BlocRepository;
import com.example.tp2.repository.ChambreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class BlocServiceImpl implements IBlocService {
    @Autowired
    BlocRepository blocRepository;
    @Autowired
    ChambreRepository chambreRepository;

    @Override
    public Bloc addBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public Bloc updateBloc(Bloc b) {
        return blocRepository.save(b);
    }

    @Override
    public void deleteBloc(Long blocId) {
        blocRepository.deleteById(blocId);
    }

    @Override
    public Bloc getBlocById(Long blocId) {
        Optional<Bloc> blocOptional = blocRepository.findById(blocId) ;
        return blocOptional.orElseThrow(() -> new EntityNotFoundException("Bloc not found with ID: " + blocId));
    }

    @Override
    public List<Bloc> getAllBlocs() {
        return blocRepository.findAll();
    }

    @Override
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) {

        Bloc bloc = blocRepository.findById(idBloc).orElse(null);

        List<Chambre> chambreList = chambreRepository.findAllByNumeroChambreIn(numChambre);
//. Affecter le bloc récupéré à chaque chambre de la liste
        for(Chambre chambre: chambreList) {chambre.setBloc(bloc);
            chambreRepository.save(chambre);
        }
        return bloc;
    }


    /** @Override
    public Optional<Bloc> affecterChambresABloc(List<Long> numChambre, String nomBloc) {
        Bloc bloc = blocRepository.findBlocByNameBloc(nomBloc);

        if (bloc != null) {
            // Convert List<Long> to List<String>
            List<String> numChambreStrings = numChambre.stream()
                    .map(String::valueOf)
                    .collect(Collectors.toList());

            List<Chambre> chambres = chambreRepository.findAllByNumeroChambreIn(numChambreStrings);

            for (Chambre chambre : chambres) {
                chambre.setBloc(bloc);
            }

            bloc.getChambres().addAll(chambres);
            blocRepository.save(bloc);
            return Optional.of(bloc);
        } else {
            return Optional.empty();
        }
    }**/

}
