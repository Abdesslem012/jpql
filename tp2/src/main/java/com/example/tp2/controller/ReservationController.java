package com.example.tp2.controller;

import com.example.tp2.entities.Foyer;
import com.example.tp2.entities.Reservation;

import com.example.tp2.service.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Reservation")
public class ReservationController {

    @Autowired
    IReservationService reservationService;

    @PostMapping("/add-reservation")
    public Reservation addReservation(
            @RequestBody Map<String, Long> requestBody){
        long idChambre = requestBody.get("idChambre");
        long cinEtudiant = requestBody.get("cinEtudiant");

        return reservationService.ajouterReservation(idChambre, cinEtudiant);
    }
    @PutMapping("/cancel-reservation/{cinEtudiant}")
    public String cancelReservation(@PathVariable long cinEtudiant) {
        Reservation canceledReservation = reservationService.annulerReservation(cinEtudiant);

        if (canceledReservation != null) {
            return "Reservation with ID: " + canceledReservation.getIdReservation() + " is canceled";
        } else {
            return "Unable to cancel reservation. Student not found or no valid reservation found.";
        }
    }
    @PutMapping("/update-reservation/{reservationId}")
    public Reservation updateReservation(@PathVariable String reservationId,@RequestBody Reservation r ){
        r.setIdReservation(reservationId);
        Reservation updatedReservation = reservationService.updateReservation(r);
        return updatedReservation;
    }
    @DeleteMapping("/delete-reservation/{reservationId}")
    public void deleteReservation(@PathVariable String reservationId){
        reservationService.deleteReservation(reservationId);
    }
    @GetMapping("/get-reservation/{reservationId}")
    public Reservation getReservationById(@PathVariable String reservationId){
        return reservationService.getReservationById(reservationId);
    }
    @GetMapping("/get-all-reservations")
    public List<Reservation> getAllReservations(){
        return reservationService.getAllReservations();
    }

}
    /**@PostMapping("/add-new-res-for-student")
    @ResponseBody
    public String ajouterReservationEtAssignerAChambreEtAEtudiant(@RequestBody Reservation reservation, @RequestParam long cin) {
        Reservation savedReservation = reservationService.ajouterReservationEtAssignerAChambreEtAEtudiant(reservation, cin);

        if (savedReservation != null) {
            return "Reservation created successfully";
        } else {
            return "Invalid chambre or etudiant";
        }**/




