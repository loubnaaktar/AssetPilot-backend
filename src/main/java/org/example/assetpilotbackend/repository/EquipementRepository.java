package org.example.assetpilotbackend.repository;

import org.example.assetpilotbackend.enums.StatutEquipement;
import org.example.assetpilotbackend.model.Equipement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Long> {

    Equipement findByNumeroSerie(String numeroSerie);

    Page<Equipement> findByStatut(StatutEquipement statut, Pageable pageable);

    long countByStatut(StatutEquipement statut);

    Page<Equipement> findByCategorie_Id(Long categorieId, Pageable pageable);

    @Query("SELECT e FROM Equipement e " +
            "WHERE (:mot IS NULL OR LOWER(e.modele) LIKE LOWER(CONCAT('%', :mot, '%'))) " +
            "AND (:statut IS NULL OR e.statut = :statut) " +
            "AND (:categorieId IS NULL OR e.categorie.id = :categorieId)")
    Page<Equipement> rechercher(@Param("mot") String mot,
                                @Param("statut") StatutEquipement statut,
                                @Param("categorieId") Long categorieId,
                                Pageable pageable);

}
