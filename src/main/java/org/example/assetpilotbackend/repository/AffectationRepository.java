package org.example.assetpilotbackend.repository;

import org.example.assetpilotbackend.enums.StatutAffectation;
import org.example.assetpilotbackend.model.Affectation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AffectationRepository extends JpaRepository<Affectation, Long> {

    Page<Affectation> findByEmploye_IdAndStatut(Long employeId, StatutAffectation statut, Pageable pageable);

    Page<Affectation> findByEquipement_IdAndStatut(Long equipementId, StatutAffectation statut, Pageable pageable);

    boolean existsByEquipement_IdAndStatut(Long equipementId, StatutAffectation statut);

    @Query("""
        SELECT COUNT(a) > 0
        FROM Affectation a
        WHERE a.employe.id = :employeId
          AND a.statut = 'ACTIF'
          AND a.equipement.categorie.id = :categorieId
    """)
    boolean existsActiveAffectationByEmployeAndCategorie(
            @Param("employeId") Long employeId,
            @Param("categorieId") Long categorieId
    );
}
