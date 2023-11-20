package com.example.tp2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Foyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idFoyer;
    private String nomFoyer;
    private String CapaciteFoyer;
    @OneToMany(mappedBy = "foyer")
    private Set<Bloc> blocs;
    @OneToOne(mappedBy = "foyer")
    private Universite universite;


}
