package org.example.assetpilotbackend.service;

import org.example.assetpilotbackend.dto.categorie.CategorieRequest;
import org.example.assetpilotbackend.dto.categorie.CategorieResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    CategorieResponse ajouterCategorie(CategorieRequest request);
    CategorieResponse getCategorieById(Long id);
    Page<CategorieResponse> getAllCategories(Pageable pageable);
    CategorieResponse modifierCategorie(Long id , CategorieRequest request);
    void supprimerCategorie(Long id);
}