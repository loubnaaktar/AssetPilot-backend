package org.example.assetpilotbackend.repository;

import org.example.assetpilotbackend.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IncidentRepository extends JpaRepository<Incident, Long> {

}