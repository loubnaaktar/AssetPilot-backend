package org.example.assetpilotbackend.service.impl;

import org.example.assetpilotbackend.mapper.AffectationMapper;
import org.example.assetpilotbackend.model.Employe;
import org.example.assetpilotbackend.repository.AffectationRepository;
import org.example.assetpilotbackend.repository.EmployeRepository;
import org.example.assetpilotbackend.repository.EquipementRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
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

    }

    @Test
    void restituerEquipement() {
    }
}