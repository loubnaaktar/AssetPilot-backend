package org.example.assetpilotbackend.repository;

import org.example.assetpilotbackend.enums.StatutEquipement;
import org.example.assetpilotbackend.model.Equipement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement, Long> {

    Equipement findByNumeroSerie(String numeroSerie);

    Page<Equipement> findByStatut(StatutEquipement statut, Pageable pageable);

    long countByStatut(StatutEquipement statut);

    Page<Equipement> findByCategorie_Id(Long categorieId, Pageable pageable);

}
