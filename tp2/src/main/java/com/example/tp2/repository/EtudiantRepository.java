package com.example.tp2.repository;

import com.example.tp2.entities.Chambre;
import com.example.tp2.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long> {
    public Etudiant findEtudiantByCin(Long cin);
}
