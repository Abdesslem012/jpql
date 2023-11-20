package com.example.tp2.service;

import com.example.tp2.entities.Etudiant;

import java.util.List;

public interface IEtudiantService {

    public Etudiant addEtudiant (Etudiant e);
    public  Etudiant updateEtudiant (Etudiant e);
    public void deleteEtudiant (Long etudiantId);
    public Etudiant getEtudiantById (Long etudiantId);
    public List<Etudiant> getAllEtudiants();
}
