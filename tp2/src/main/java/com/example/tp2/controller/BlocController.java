package com.example.tp2.controller;

import com.example.tp2.entities.Bloc;
import com.example.tp2.entities.Chambre;
import com.example.tp2.service.IBlocService;
import com.example.tp2.service.IChambreService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/Bloc")
public class BlocController {

    @Autowired
    IBlocService blocService;
    IChambreService chambreService;

    /**@PostMapping("/affecterChambres")
    public String affecterChambresABloc(@RequestBody Map<String, Object> request) {
        if (request.containsKey("b") && request.containsKey("numChambres")) {
            Map<String, Object> b = (Map<String, Object>) request.get("b");
            List<Long> numChambres = (List<Long>) request.get("numChambres");
            String nameBloc = (String) b.get("nameBloc");

            blocService.affecterChambresABloc(numChambres, nameBloc);

            return "Chambres affectées au bloc avec succès.";
        } else {
            return "Invalid request. Please provide the required data.";
        }
    }**/
    @PutMapping("/affecterChambresABloc/{idBloc}")
    public Bloc affecterChambresABloc(@RequestBody List<Long> numChambre,
    @PathVariable("idBloc") long idBloc)
    {
        return chambreService.affecterChambresABloc(numChambre, idBloc);
    }



    @PostMapping("/add-bloc")
    public Bloc addbloc(@RequestBody Bloc b){
        Bloc bloc = blocService.addBloc(b);
        return bloc;
    }
    @PutMapping("/update-bloc/{blocId}")
    public Bloc updateBloc(@PathVariable Long blocId, @RequestBody Bloc b){
        b.setIdBloc(blocId);
        Bloc updatedBloc = blocService.updateBloc(b);
        return  updatedBloc;
    }
    @DeleteMapping("/delete-bloc/{blocId}")
    public void deletebloc(@PathVariable Long blocId){
        blocService.deleteBloc(blocId);
    }
    @GetMapping("/get-bloc/{blocId}")
    public Bloc getBlocById(@PathVariable Long blocId){
        return blocService.getBlocById(blocId);
    }
    @GetMapping("/get-all-blocs")
    public List<Bloc> getAllBlocs(){
        return blocService.getAllBlocs();
    }
}
