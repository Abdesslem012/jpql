package com.example.tp2.controller;

import com.example.tp2.entities.Etudiant;
import com.example.tp2.service.EtudiantServiceImpl;
import com.example.tp2.service.IEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Etudiant")
public class EtudiantController {

    @Autowired
    IEtudiantService etudiantService;

    @PostMapping("/add-etudiant")
    public Etudiant addEtudiant(@RequestBody Etudiant e){
        Etudiant etudiant = etudiantService.addEtudiant(e);
        return etudiant;
    }
    @PutMapping("/update-etudiant/{etudiantId}")
    public Etudiant updateEtudiant(@PathVariable Long etudiantId,@RequestBody Etudiant e){
        e.setIdEtudiant(etudiantId);
        Etudiant updatedEtudiant = etudiantService.updateEtudiant(e);
        return  updatedEtudiant;
    }
    @DeleteMapping("/delete-etudiant/{etudiantId}")
    public void deleteEtudiant(@PathVariable Long etudiantId){
        etudiantService.deleteEtudiant(etudiantId);
    }
    @GetMapping("/get-etudiant/{etudiantId}")
    public Etudiant getEtudiantById(@PathVariable Long etudiantId){
        return etudiantService.getEtudiantById(etudiantId);
    }
    @GetMapping("/get-all-etudiants")
    public List<Etudiant> getAllEtudiants(){
        return etudiantService.getAllEtudiants();
    }
}
