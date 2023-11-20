package com.example.tp2.service;

import com.example.tp2.entities.Bloc;

import java.util.List;
import java.util.Optional;

public interface IBlocService {
    public Bloc addBloc (Bloc b);
    public Bloc updateBloc (Bloc b);
    public void deleteBloc (Long blocId);
    public Bloc getBlocById (Long blocId);
    public List<Bloc> getAllBlocs();
    /**Optional <Bloc> affecterChambresABloc (List<Long> numChambre, String nomBloc) ;**/
    public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc) ;
}
