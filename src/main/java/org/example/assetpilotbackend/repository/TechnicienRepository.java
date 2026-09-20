package org.example.assetpilotbackend.repository;

import org.example.assetpilotbackend.model.Technicien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TechnicienRepository extends JpaRepository<Technicien, Long> {
    Page<Technicien> findBySpecialiteContainingIgnoreCase(String specialite, Pageable pageable);

    List<Technicien> findBySpecialiteIgnoreCase(String specialite);

    @Query("SELECT DISTINCT t.specialite FROM Technicien t WHERE t.specialite IS NOT NULL AND t.specialite <> ''")
    List<String> findDistinctSpecialites();
}
