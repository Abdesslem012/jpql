package com.example.tp2.repository;

import com.example.tp2.entities.Bloc;
import com.example.tp2.entities.Chambre;
import com.example.tp2.entities.TypeChambre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre,Long> {

    List<Chambre> findAllByNumeroChambreIn(List<Long> numeroChambre);
    Chambre findByNumeroChambre(Long numeroChambre);
    public List<Chambre> findChambresByBloc (Bloc bloc);
    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeC")
    List<Chambre> findChambresByBlocIdAndType(@Param("idBloc") long idBloc, @Param("typeC") TypeChambre typeC);
    @Query("SELECT c FROM Chambre c " +
            "JOIN c.bloc b " +
            "JOIN b.foyer f " +
            "LEFT JOIN c.reservations r " +
            "WHERE f.nomFoyer = :nomUniversite " +
            "AND c.typeC = :type " +
            "AND (r IS NULL OR r.estValide = false)")
    List<Chambre> findUnreservedRoomsByDormitoryAndRoomType(
            @Param("nomUniversite") String nomUniversite,
            @Param("type") TypeChambre type
    );



}
