package org.example.assetpilotbackend.service.impl;

import org.example.assetpilotbackend.dto.affectation.AffectationRequest;
import org.example.assetpilotbackend.dto.affectation.AffectationResponse;
import org.example.assetpilotbackend.enums.StatutAffectation;
import org.example.assetpilotbackend.enums.StatutEquipement;
import org.example.assetpilotbackend.mapper.AffectationMapper;
import org.example.assetpilotbackend.model.Affectation;
import org.example.assetpilotbackend.model.Categorie;
import org.example.assetpilotbackend.model.Employe;
import org.example.assetpilotbackend.model.Equipement;
import org.example.assetpilotbackend.repository.AffectationRepository;
import org.example.assetpilotbackend.repository.EmployeRepository;
import org.example.assetpilotbackend.repository.EquipementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AffectationServiceImplTest {

    @Mock
    private AffectationRepository affectationRepository;

    @Mock
    private EmployeRepository employeRepository;

    @Mock
    private EquipementRepository equipementRepository;

    @Mock
    AffectationMapper affectationMapper;

    @InjectMocks
    AffectationServiceImpl affectationService;


    @Test
    void creerAffectation() {
        long employeId = 1L;
        long equipementId = 2L;

        AffectationRequest request = new AffectationRequest();
        request.setEmployeId(employeId);
        request.setEquipementId(equipementId);
        request.setDateDebut(LocalDate.now());

        Employe employe = new Employe();
        employe.setAffectations(new ArrayList<>());

        Categorie categorie = new Categorie();
        categorie.setId(10L);
        categorie.setNom("Ordinateur");

        Equipement equipement = new Equipement();
        equipement.setStatut(StatutEquipement.EN_STOCK);
        equipement.setCategorie(categorie);

        Affectation savedAffectation = new Affectation();
        AffectationResponse expectedResponse = new AffectationResponse();

        when(employeRepository.findById(employeId)).thenReturn(Optional.of(employe));
        when(equipementRepository.findById(equipementId)).thenReturn(Optional.of(equipement));
        when(affectationRepository.save(any(Affectation.class))).thenReturn(savedAffectation);
        when(affectationMapper.toDTO(savedAffectation)).thenReturn(expectedResponse);

        AffectationResponse result = affectationService.creerAffectation(request);

        assertNotNull(result);
        assertEquals(StatutEquipement.AFFECTE, equipement.getStatut());
        verify(equipementRepository).save(equipement);
        verify(affectationRepository).save(any(Affectation.class));

    }

    @Test
    void restituerEquipement() {

        long affectationId = 1L;

        Equipement equipement = new Equipement();
        equipement.setStatut(StatutEquipement.AFFECTE);

        Affectation affectation = new Affectation();
        affectation.setStatut(StatutAffectation.ACTIF);
        affectation.setEquipement(equipement);

        AffectationResponse expectedResponse = new AffectationResponse();

        when(affectationRepository.findById(affectationId)).thenReturn(Optional.of(affectation));
        when(affectationRepository.save(affectation)).thenReturn(affectation);
        when(affectationMapper.toDTO(affectation)).thenReturn(expectedResponse);

        AffectationResponse result = affectationService.restituerEquipement(affectationId);

        assertNotNull(result);
        assertEquals(StatutAffectation.RESTITUE, affectation.getStatut());
        assertEquals(StatutEquipement.EN_STOCK, equipement.getStatut());
        assertNotNull(affectation.getDateFin());
        verify(equipementRepository).save(equipement);
        verify(affectationRepository).save(affectation);
    }
}