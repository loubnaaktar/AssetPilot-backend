package org.example.assetpilotbackend.repository;

import org.example.assetpilotbackend.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

    Categorie findByNomIgnoreCase(String nom);

}