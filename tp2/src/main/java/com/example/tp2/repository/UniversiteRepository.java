package com.example.tp2.repository;

import com.example.tp2.entities.Universite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversiteRepository extends JpaRepository<Universite,Long> {
    @Query("SELECT u FROM Universite u WHERE u.nomUniversite = :nomUniversite")
    Universite findUniversiteByNom(String nomUniversite);
}
