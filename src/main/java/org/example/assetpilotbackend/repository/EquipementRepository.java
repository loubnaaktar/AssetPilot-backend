package org.example.assetpilotbackend.repository;

import org.example.assetpilotbackend.model.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipementRepository extends JpaRepository<Equipement, Long> {

}