package com.example.tp2.repository;

import com.example.tp2.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,String> {
    @Query("SELECT r FROM Reservation r " +
            "JOIN r.etudiants e " +
            "WHERE r.anneeUniversitaire = :academicYear " +
            "AND e.ecole = :schoolName")
    List<Reservation> findReservationsByAcademicYearAndSchoolName(
            @Param("academicYear") Date academicYear,
            @Param("schoolName") String schoolName
    );
}
