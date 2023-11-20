package com.example.tp2.service;


import com.example.tp2.entities.Bloc;
import com.example.tp2.entities.Chambre;
import com.example.tp2.entities.TypeChambre;

import java.util.List;

public interface IChambreService {

    public Chambre addChambre (Chambre c);
    public Chambre updateChambre (Chambre c);
    public void deleteChambre (Long chambreId);
    public Chambre getChambreById (Long chambreId);
    public List<Chambre> getAllChambres();

    Bloc affecterChambresABloc(List<Long> numChambre, long idBloc);
    List<Chambre> getChambresByNomUniversite(String nomUniversite);
    List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC);
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type);
}
