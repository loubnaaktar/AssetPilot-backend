package org.example.assetpilotbackend.service.impl;

import org.example.assetpilotbackend.mapper.EquipementMapper;
import org.example.assetpilotbackend.model.Equipement;
import org.example.assetpilotbackend.repository.AffectationRepository;
import org.example.assetpilotbackend.repository.EquipementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EquipementServiceImplTest {

    @Mock
    private EquipementRepository repo;
    @Mock
    private EquipementMapper mapper;
    @Mock
    private CategorieServiceImpl service;
    @Mock
    private AffectationRepository affectationRepository;
    @InjectMocks
    private EquipementServiceImpl equipementService;

    @Test
    void supprimerEquipement() {
        long id = 1L;

        Equipement equipement = new Equipement();

        when(repo.findById(id)).thenReturn(Optional.of(equipement));

        equipementService.supprimerEquipement(id);

        verify(repo).delete(equipement);
    }
}