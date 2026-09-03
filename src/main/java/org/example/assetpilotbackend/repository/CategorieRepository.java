package org.example.assetpilotbackend.repository;

import org.example.assetpilotbackend.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {

}