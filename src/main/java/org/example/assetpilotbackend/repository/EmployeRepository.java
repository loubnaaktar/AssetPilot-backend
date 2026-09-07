package org.example.assetpilotbackend.repository;

import org.example.assetpilotbackend.model.Employe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
    Employe findByMatricule(String matricule);
}
