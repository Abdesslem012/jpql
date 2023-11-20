package com.example.tp2.controller;

import com.example.tp2.entities.Foyer;
import com.example.tp2.service.IFoyerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Foyer")
public class FoyerController {

    @Autowired
    IFoyerService foyerService;

    @PutMapping("/ajouterFoyerEtAffecterAUniversite/{idUniversite}")
    public Foyer ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer,
                                                   @PathVariable("idUniversite") long idUniversite) {
        return foyerService.ajouterFoyerEtAffecterAUniversite(foyer,
                idUniversite);
    }

    @PostMapping("/add-foyer")
    public Foyer addFoyer(@RequestBody Foyer f){
       return foyerService.addFoyer(f);
    }
    @PutMapping("/update-foyer/{foyerId}")
    public Foyer updateFoyer(@PathVariable Long foyerId,@RequestBody Foyer f ){
        f.setIdFoyer(foyerId);
        Foyer updatedfoyer = foyerService.updateFoyer(f);
        return updatedfoyer;
    }
    @DeleteMapping("/delete-foyer/{foyerId}")
    public void deleteFoyer(@PathVariable Long foyerId){
        foyerService.deleteFoyer(foyerId);
    }
    @GetMapping("/get-foyer/{foyerId}")
    public Foyer getFoyerById(@PathVariable Long foyerId){
       return foyerService.getFoyerById(foyerId);
    }
    @GetMapping("/get-all-foyers")
    public List<Foyer> getAllFoyers(){
        return foyerService.getAllFoyers();
    }
}
