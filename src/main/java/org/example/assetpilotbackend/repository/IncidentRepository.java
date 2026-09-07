package org.example.assetpilotbackend.repository;

import org.example.assetpilotbackend.enums.StatutIncident;
import org.example.assetpilotbackend.model.Incident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    Page<Incident> findByDeclarePar_Id(Long employeId, Pageable pageable);

    Page<Incident> findByTraitePar_Id(Long technicienId, Pageable pageable);

    Page<Incident> findByStatut(StatutIncident statut, Pageable pageable);
}
