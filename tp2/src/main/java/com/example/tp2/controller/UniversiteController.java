package com.example.tp2.controller;


import com.example.tp2.entities.Foyer;
import com.example.tp2.entities.Universite;
import com.example.tp2.service.IFoyerService;
import com.example.tp2.service.IUniversiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/Universite")
public class UniversiteController {

    @Autowired
    IUniversiteService universiteService;
    IFoyerService foyerService;

    @PutMapping("/affecterFoyerAUniversite/{idFoyer}/{nomUniversite}")
    public Universite affecterFoyerAUniversite(@PathVariable("idFoyer") long idFoyer,
                                               @PathVariable("nomUniversite") String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(idFoyer, nomUniversite);
    }
    @PutMapping("/desaffecterFoyerAUniversite/{idUniversite}")
    public Universite desaffecterFoyerAUniversite(
            @PathVariable("idUniversite") long idUniversite) {
        return universiteService.desaffecterFoyerAUniversite(
                idUniversite);
    }
    @PutMapping("/ajouterFoyerEtAffecterAUniversite/{idUniversite}")
    public Foyer ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer,
                                                   @PathVariable("idUniversite") long idUniversite) {
        return foyerService.ajouterFoyerEtAffecterAUniversite(foyer,
                idUniversite);
    }



    @PostMapping("/add-universite")
    public Universite addUniversite(@RequestBody Universite U){
        return universiteService.addUniversite(U);
    }
    @PutMapping("/update-universite/{universiteId}")
    public Universite updateUniversite(@PathVariable Long universiteId,@RequestBody Universite u ){
        u.setIdUniversite(universiteId);
        Universite updatedUniversite = universiteService.updateUniversite(u);
        return updatedUniversite;
    }
    @DeleteMapping("/delete-universite/{universiteId}")
    public void deleteUniversite(@PathVariable Long universiteId){
        universiteService.deleteUniversite(universiteId);
    }
    @GetMapping("/get-universite/{universiteId}")
    public Universite getUniversiteById(@PathVariable Long universiteId){
        return universiteService.getUniversiteById(universiteId);
    }
    @GetMapping("/get-all-universites")
    public List<Universite> getAllUniversites(){
        return universiteService.getAllUniversites();
    }

}
