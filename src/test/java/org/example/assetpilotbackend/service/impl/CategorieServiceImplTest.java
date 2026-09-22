package org.example.assetpilotbackend.service.impl;

import org.example.assetpilotbackend.dto.categorie.CategorieRequest;
import org.example.assetpilotbackend.dto.categorie.CategorieResponse;
import org.example.assetpilotbackend.mapper.CategorieMapper;
import org.example.assetpilotbackend.model.Categorie;
import org.example.assetpilotbackend.repository.CategorieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategorieServiceImplTest {

    @Mock
    private CategorieRepository repo;
    @Mock
    private CategorieMapper mapper;
    @InjectMocks
    private CategorieServiceImpl categorieService;

    @Test
    void modifierCategorie() {
        long id = 1L;

        Categorie categorie = new Categorie();
        categorie.setNom("Ancien nom");

        CategorieRequest request = new CategorieRequest();
        request.setNom("Nouveau nom");
        request.setDescription("Description mise à jour");

        CategorieResponse expectedResponse = new CategorieResponse();

        when(repo.findById(id)).thenReturn(Optional.of(categorie));
        when(repo.save(categorie)).thenReturn(categorie);
        when(mapper.toDto(categorie)).thenReturn(expectedResponse);

        categorieService.modifierCategorie(id, request);

        assertEquals("Nouveau nom", categorie.getNom());
        assertEquals("Description mise à jour", categorie.getDescription());
        verify(repo).save(categorie);
    }
}