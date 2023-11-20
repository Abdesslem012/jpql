package com.example.tp2.service;

import com.example.tp2.entities.Chambre;
import com.example.tp2.entities.Etudiant;
import com.example.tp2.entities.Reservation;
import com.example.tp2.entities.TypeChambre;
import com.example.tp2.repository.ChambreRepository;
import com.example.tp2.repository.EtudiantRepository;
import com.example.tp2.repository.ReservationRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.example.tp2.entities.TypeChambre.*;

@AllArgsConstructor
@Service
public class ReservationServiceImpl implements IReservationService{

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    private ChambreRepository chambreRepository;
    @Autowired
    private EtudiantRepository etudiantRepository;

    private int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    @Override
    public Reservation addReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public Reservation updateReservation(Reservation r) {
        return reservationRepository.save(r);
    }

    @Override
    public void deleteReservation(String reservationId) {
        reservationRepository.deleteById(reservationId);
    }

    @Override
    public Reservation getReservationById(String reservationId) {
        Optional<Reservation> reservationOptional = reservationRepository.findById(reservationId);
        return reservationOptional.orElseThrow(() -> new EntityNotFoundException("Reservation not found with ID: " + reservationId));
    }

    @Override
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation ajouterReservation(long idChambre, long cinEtudiant) {
        Chambre chambre = chambreRepository.findById(idChambre).orElse(null);
        Etudiant etudiant = etudiantRepository.findEtudiantByCin(cinEtudiant);

        if (chambre == null || etudiant == null) {
            return null; // Handle scenario where the room or student doesn't exist
        }

        if (!isRoomCapacityReached(chambre)) {
            Reservation reservation = createReservation(chambre, etudiant);
            if (reservation != null) {
                chambre.getReservations().add(reservation);
                etudiant.getReservations().add(reservation);
                return reservationRepository.save(reservation);
            }
        } else {
            return null; // Handle scenario where room capacity is reached
        }
        return null;
    }

    @Override
    public Reservation annulerReservation(long cinEtudiant) {
        Etudiant etudiant = etudiantRepository.findEtudiantByCin(cinEtudiant);

        if (etudiant == null) {
            return null; // Handle scenario where the student is not found
        }

        Reservation reservation = etudiant.getReservations().stream()
                .filter(res -> res.getEstValide())
                .findFirst()
                .orElse(null);

        if (reservation != null) {
            reservation.setEstValide(false);
            etudiant.getReservations().remove(reservation);
            reservationRepository.save(reservation); // Update reservation status
        }

        return reservation;
    }

    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversite, String nomUniversite) {
        return reservationRepository.findReservationsByAcademicYearAndSchoolName(anneeUniversite, nomUniversite);
    }

    private boolean isRoomCapacityReached(Chambre chambre) {
        TypeChambre chambreType = chambre.getTypeC();

        int maxCapacity;
        switch (chambreType) {
            case SIMPLE:
                maxCapacity = 1;
                break;
            case DOUBLE:
                maxCapacity = 2;
                break;
            case TRIPLE:
                maxCapacity = 3;
                break;
            default:
                return false; // Unknown room type or no type specified
        }

        return chambre.getReservations().size() >= maxCapacity;
    }

    private int getMaxCapacityByRoomType(TypeChambre type) {
        switch (type) {
            case SIMPLE:
                return 1;
            case DOUBLE:
                return 2;
            case TRIPLE:
                return 3;
            default:
                return 0; // Set default capacity as needed
        }
    }

    private void updateRoomCapacity(TypeChambre type, Chambre chambre) {
        int currentCapacity = chambre.getReservations().size();
        int maxCapacity = getMaxCapacityByRoomType(type);

        if (currentCapacity > maxCapacity) {
            // Adjust the room's reservations if it exceeds the maximum capacity
            Set<Reservation> reservations = chambre.getReservations();
            // Remove extra reservations beyond the maximum capacity
            List<Reservation> extraReservations = reservations.stream()
                    .skip(maxCapacity)
                    .collect(Collectors.toList());

            extraReservations.forEach(res -> {
                reservations.remove(res);
                reservationRepository.delete(res); // Delete the extra reservation
            });
        }
    }

    private String generateReservationId(Chambre chambre) {
        // Assuming chambre.getNumeroChambre() returns a Long value
        String numChambre = String.valueOf(chambre.getNumeroChambre());

        // Access the associated Bloc entity to get the nameBloc
        String nomBloc = chambre.getBloc().getNameBloc();

        // Generate the reservation ID in the specified format
        return numChambre + "-" + nomBloc + "-" + getCurrentYear();
    }
    private Reservation createReservation(Chambre chambre, Etudiant etudiant) {
        Reservation reservation = new Reservation();
        // Generate the reservation ID
        String reservationId = generateReservationId(chambre);
        reservation.setIdReservation(reservationId);
        // Set other properties for the reservation as needed

        return reservation;
    }




    /** public Reservation ajouterReservationEtAssignerAChambreEtAEtudiant(Reservation res, String numChambre, long cin) {
        // Retrieve the chambre based on the provided numChambre
        Chambre chambre = chambreRepository.findByNumeroChambre(numChambre);
        if (chambre != null) {
            // Check if the chambre's capacity is not reached
            if (chambre.getCapacite() > chambre.getReservations().size()) {
                // Assign the reservation to the chambre
                chambre.getReservations().add(res);
            } else {
                // Handle the case where the chambre's capacity is reached
                // You can throw an exception or return null as needed.
                return null;
            }
        } else {
            // Handle the case where the chambre with the specified numChambre does not exist
            // You can throw an exception or return null as needed.
            return null;
        }

        // Retrieve the étudiant based on the provided cin
        Etudiant etudiant = etudiantRepository.findEtudiantByCin(cin);
        if (etudiant != null) {
            etudiant.getReservations().add(res);
            res.getEtudiants().add(etudiant);
        } else {
            // Handle the case where the étudiant with the specified cin does not exist
            // You can throw an exception or return null as needed.
            return null;
        }

        // Save the new reservation
        return reservationRepository.save(res);
    }**/
}