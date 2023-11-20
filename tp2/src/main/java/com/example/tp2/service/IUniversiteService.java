package com.example.tp2.service;


import com.example.tp2.entities.Foyer;
import com.example.tp2.entities.Universite;

import java.util.List;
import java.util.Optional;

public interface IUniversiteService {

    public Universite addUniversite(Universite u);

    public Universite updateUniversite(Universite u);

    public void deleteUniversite(Long universiteId);

    public Universite getUniversiteById(Long universiteId);

    public List<Universite> getAllUniversites();

    public Universite desaffecterFoyerAUniversite( long idUniversite);


    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite);
    public Foyer ajouterFoyerEtAffecterAUniversite (Foyer foyer, long idUniversite) ;
}