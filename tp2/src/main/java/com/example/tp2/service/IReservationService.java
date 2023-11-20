package com.example.tp2.service;


import com.example.tp2.entities.Reservation;

import java.util.Date;
import java.util.List;

public interface IReservationService {

    public Reservation addReservation (Reservation r);
    public Reservation updateReservation (Reservation r);
    public void deleteReservation (String reservationId);
    public Reservation getReservationById (String reservationId);
    public List<Reservation> getAllReservations();
    public Reservation ajouterReservation (long idChambre, long cinEtudiant) ;
    public Reservation annulerReservation (long cinEtudiant) ;
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite);
}
