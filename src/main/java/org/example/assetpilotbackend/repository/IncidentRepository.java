package org.example.assetpilotbackend.repository;

import org.example.assetpilotbackend.enums.NiveauUrgence;
import org.example.assetpilotbackend.enums.StatutIncident;
import org.example.assetpilotbackend.model.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    Page<Incident> findByDeclarePar_Id(Long employeId, Pageable pageable);

    Page<Incident> findByTraitePar_Id(Long technicienId, Pageable pageable);

    Page<Incident> findByStatut(StatutIncident statut, Pageable pageable);

    long countByStatut(StatutIncident statut);

    long countByTraitePar_IdAndStatutNot(Long technicienId, StatutIncident statut);

    @Query("SELECT i FROM Incident i WHERE " +
            "(:mot IS NULL OR :mot = '' " +
            "   OR i.description LIKE CONCAT('%', :mot, '%') " +
            "   OR i.equipement.NumeroSerie LIKE CONCAT('%', :mot, '%')) " +
            "AND (:statut IS NULL OR i.statut = :statut) " +
            "AND (:niveauUrgence IS NULL OR i.niveauUrgence = :niveauUrgence) " +
            "AND (:nonAssigne IS NULL OR :nonAssigne = FALSE OR i.traitePar IS NULL)")
    Page<Incident> findAllFiltered(
            @Param("mot") String mot,
            @Param("statut") StatutIncident statut,
            @Param("niveauUrgence") NiveauUrgence niveauUrgence,
            @Param("nonAssigne") Boolean nonAssigne,
            Pageable pageable);
}
