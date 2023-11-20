package com.example.tp2.controller;

import com.example.tp2.entities.Chambre;
import com.example.tp2.entities.TypeChambre;
import com.example.tp2.service.IChambreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Chambre")
public class ChambreController {

    @Autowired
    IChambreService chambreService;

    @PostMapping("/add-chambre")
    public Chambre addChambre(@RequestBody Chambre c){
        Chambre chambre = chambreService.addChambre(c);
        return chambre;
    }
    @PutMapping("/update-chambre/{chambreId}")
     public Chambre updateChambre(@PathVariable Long chambreId, @RequestBody Chambre c){
        c.setIdChambre(chambreId);
        Chambre updatedChambre =chambreService.updateChambre(c);
        return updatedChambre;
    }
    @DeleteMapping("/delete-chambre/{chambreId}")
    public void deleteChambre(@PathVariable Long chambreId){
        chambreService.getChambreById(chambreId);
    }
    @GetMapping("/get-chambre/{chambreId}")
    public Chambre getChambreById(@PathVariable Long chambreId){
        return chambreService.getChambreById(chambreId);
    }
    @GetMapping("/get-all-chambres")
    public List<Chambre> getAllChambres(){
     return chambreService.getAllChambres();
    }
    @GetMapping("/chambres/by-nom-universite/{nomUniversite}")
    public List<Chambre> getChambresByNomUniversite(@PathVariable String nomUniversite) {
        return chambreService.getChambresByNomUniversite(nomUniversite);
    }
    @GetMapping("/chambres/par-bloc")
    public List<Chambre> getChambresParBlocEtType(
            @RequestParam("idBloc") long idBloc,
            @RequestParam("typeC") TypeChambre typeC) {
        return chambreService.getChambresParBlocEtType(idBloc, typeC);
    }
    @GetMapping("/chambre/non-reservé")
    public List<Chambre> getUnreservedRooms(
            @RequestParam("nomUniversite") String nomUniversite,
            @RequestParam("type") TypeChambre type
    ) {
        return chambreService.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, type);
    }
}
