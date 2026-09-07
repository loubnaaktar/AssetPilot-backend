package org.example.assetpilotbackend.repository;

import org.example.assetpilotbackend.model.Technicien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechnicienRepository extends JpaRepository<Technicien, Long> {
    Page<Technicien> findBySpecialiteContainingIgnoreCase(String specialite, Pageable pageable);
}
