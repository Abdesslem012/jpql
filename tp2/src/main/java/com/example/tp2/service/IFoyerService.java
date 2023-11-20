package com.example.tp2.service;


import com.example.tp2.entities.Foyer;

import java.util.List;

public interface IFoyerService {

    public Foyer addFoyer (Foyer f);
    public Foyer updateFoyer (Foyer f);
    public void deleteFoyer (Long foyerId);
    public Foyer getFoyerById (Long foyerId);
    public List<Foyer> getAllFoyers();

    Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite);
}
