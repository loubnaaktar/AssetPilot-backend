package org.example.assetpilotbackend.service.impl;

import org.example.assetpilotbackend.dto.incident.IncidentRequest;
import org.example.assetpilotbackend.dto.incident.IncidentResponse;
import org.example.assetpilotbackend.dto.incident.IncidentUpdateRequest;
import org.example.assetpilotbackend.enums.StatutEquipement;
import org.example.assetpilotbackend.enums.StatutIncident;
import org.example.assetpilotbackend.mapper.IncidentMapper;
import org.example.assetpilotbackend.model.Employe;
import org.example.assetpilotbackend.model.Equipement;
import org.example.assetpilotbackend.model.Incident;
import org.example.assetpilotbackend.model.Technicien;
import org.example.assetpilotbackend.repository.EmployeRepository;
import org.example.assetpilotbackend.repository.EquipementRepository;
import org.example.assetpilotbackend.repository.AffectationRepository;
import org.example.assetpilotbackend.repository.IncidentRepository;
import org.example.assetpilotbackend.repository.TechnicienRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class IncidentServiceImplTest {

    @Mock
    private IncidentRepository incidentRepository;
    @Mock
    private EmployeRepository employeRepository;
    @Mock
    private EquipementRepository equipementRepository;
    @Mock
    private TechnicienRepository technicienRepository;
    @Mock
    private AffectationRepository affectationRepository;
    @Mock
    private IncidentMapper incidentMapper;
    @InjectMocks
    private IncidentServiceImpl incidentService;

    @Test
    void declarerIncident() {
        long employeId = 1L;
        long equipementId = 2L;

        IncidentRequest request = new IncidentRequest();
        request.setEquipementId(equipementId);

        Employe employe = new Employe();
        Equipement equipement = new Equipement();
        Incident incident = new Incident();
        Incident savedIncident = new Incident();
        IncidentResponse expectedResponse = new IncidentResponse();

        when(employeRepository.findById(employeId)).thenReturn(Optional.of(employe));
        when(equipementRepository.findById(equipementId)).thenReturn(Optional.of(equipement));
        when(incidentMapper.toEntity(request)).thenReturn(incident);
        when(incidentRepository.save(any(Incident.class))).thenReturn(savedIncident);
        when(incidentMapper.toDTO(savedIncident)).thenReturn(expectedResponse);

        IncidentResponse result = incidentService.declarerIncident(employeId, request);

        assertNotNull(result);
        assertEquals(StatutEquipement.EN_PANNE, equipement.getStatut());
        verify(equipementRepository).save(equipement);
        verify(incidentRepository).save(incident);
    }

    @Test
    void assignerTechnicien() {
        long incidentId = 1L;
        long technicienId = 3L;

        Equipement equipement = new Equipement();
        equipement.setStatut(StatutEquipement.EN_PANNE);
        Incident incident = new Incident();
        incident.setEquipement(equipement);

        Technicien technicien = new Technicien();
        IncidentResponse expectedResponse = new IncidentResponse();

        when(incidentRepository.findById(incidentId)).thenReturn(Optional.of(incident));
        when(technicienRepository.findById(technicienId)).thenReturn(Optional.of(technicien));
        when(incidentRepository.save(incident)).thenReturn(incident);
        when(incidentMapper.toDTO(incident)).thenReturn(expectedResponse);

        IncidentResponse result = incidentService.assignerTechnicien(incidentId,technicienId);

        assertNotNull(result);
        assertEquals(StatutIncident.EN_COURS, incident.getStatut());
        assertEquals(StatutEquipement.EN_REPARATION, equipement.getStatut());
        assertEquals(technicien, incident.getTraitePar());
        verify(equipementRepository).save(equipement);
    }

    @Test
    void mettreAJourIncident() {

        long incidentId = 1L;

        Equipement equipement = new Equipement();
        Incident incident = new Incident();
        incident.setEquipement(equipement);

        IncidentUpdateRequest request = new IncidentUpdateRequest();
        request.setStatut(StatutIncident.RESOLU);
        request.setRapportIntervention("Problème réparé");

        IncidentResponse expectedResponse = new IncidentResponse();

        when(incidentRepository.findById(incidentId)).thenReturn(Optional.of(incident));
        when(incidentRepository.save(incident)).thenReturn(incident);
        when(incidentMapper.toDTO(incident)).thenReturn(expectedResponse);

        IncidentResponse result = incidentService.mettreAJourIncident(incidentId, request);

        assertNotNull(result);
        assertEquals(StatutIncident.RESOLU, incident.getStatut());
        assertEquals(StatutEquipement.EN_STOCK, equipement.getStatut());
        assertNotNull(incident.getDateResolution());
        verify(equipementRepository).save(equipement);
    }
}