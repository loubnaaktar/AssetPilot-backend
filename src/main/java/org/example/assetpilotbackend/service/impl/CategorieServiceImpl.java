package org.example.assetpilotbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.categorie.CategorieRequest;
import org.example.assetpilotbackend.dto.categorie.CategorieResponse;
import org.example.assetpilotbackend.exception.ResourceNotFoundException;
import org.example.assetpilotbackend.mapper.CategorieMapper;
import org.example.assetpilotbackend.model.Categorie;
import org.example.assetpilotbackend.repository.CategorieRepository;
import org.example.assetpilotbackend.service.CategoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategorieServiceImpl implements CategoryService {

    private final CategorieRepository repo;
    private final CategorieMapper mapper;
    @Override
    @Transactional
    public CategorieResponse ajouterCategorie(CategorieRequest request) {
        Categorie categorie = repo.save(mapper.toEntity(request));
        return mapper.toDto(categorie);
    }

    @Override
    public CategorieResponse getCategorieById(Long id) {
        return mapper.toDto(getCategorieEntity(id));
    }

    @Override
    public Page<CategorieResponse> getAllCategories(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional
    public CategorieResponse modifierCategorie(Long id, CategorieRequest request) {
        Categorie categorie = getCategorieEntity(id);
        categorie.setNom(request.getNom());
        categorie.setDescription(request.getDescription());
        return mapper.toDto(repo.save(categorie));
    }

    @Override
    @Transactional
    public void supprimerCategorie(Long id) {
        repo.delete(getCategorieEntity(id));
    }

    public Categorie getCategorieEntity(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Catégorie introuvable avec id: " + id));
    }
}