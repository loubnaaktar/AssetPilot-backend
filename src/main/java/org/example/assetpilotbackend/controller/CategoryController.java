package org.example.assetpilotbackend.controller;

import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.affectation.AffectationResponse;
import org.example.assetpilotbackend.dto.categorie.CategorieRequest;
import org.example.assetpilotbackend.dto.categorie.CategorieResponse;
import org.example.assetpilotbackend.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CategorieResponse ajouterCategory(@Valid @RequestBody CategorieRequest request){
        return categoryService.ajouterCategorie(request);
    }

    @GetMapping
    public Page<CategorieResponse> getAllCategories(Pageable pageable){
        return categoryService.getAllCategories(pageable);
    }

    @GetMapping("/{id}")
    public CategorieResponse chercherParId(@PathVariable Long id){
        return categoryService.getCategorieById(id);
    }

    @PutMapping("/{id}")
        @PreAuthorize("hasRole('ADMIN')")
        public CategorieResponse modifierCategorie(@PathVariable Long id, @Valid @RequestBody CategorieRequest request){
        return categoryService.modifierCategorie(id,request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void supprimerCategorie(@PathVariable Long id){
        categoryService.supprimerCategorie(id);
    }

}