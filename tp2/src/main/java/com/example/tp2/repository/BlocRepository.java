package com.example.tp2.repository;

import com.example.tp2.entities.Bloc;
import com.example.tp2.entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlocRepository extends JpaRepository<Bloc,Long> {
    @Query("SELECT u FROM Bloc u WHERE u.nameBloc = :nameBloc")
    Bloc findBlocByNameBloc(String nameBloc);
}
