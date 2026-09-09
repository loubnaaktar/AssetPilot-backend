package org.example.assetpilotbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.incident.IncidentRequest;
import org.example.assetpilotbackend.dto.incident.IncidentResponse;
import org.example.assetpilotbackend.dto.incident.IncidentUpdateRequest;
import org.example.assetpilotbackend.enums.StatutEquipement;
import org.example.assetpilotbackend.enums.StatutIncident;
import org.example.assetpilotbackend.exception.ResourceNotFoundException;
import org.example.assetpilotbackend.mapper.IncidentMapper;
import org.example.assetpilotbackend.model.Employe;
import org.example.assetpilotbackend.model.Equipement;
import org.example.assetpilotbackend.model.Incident;
import org.example.assetpilotbackend.model.Technicien;
import org.example.assetpilotbackend.repository.EmployeRepository;
import org.example.assetpilotbackend.repository.EquipementRepository;
import org.example.assetpilotbackend.repository.IncidentRepository;
import org.example.assetpilotbackend.repository.TechnicienRepository;
import org.example.assetpilotbackend.service.IncidentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentRepository;
    private final EmployeRepository employeRepository;
    private final EquipementRepository equipementRepository;
    private final TechnicienRepository technicienRepository;
    private final IncidentMapper incidentMapper;

    @Override
    @Transactional
    public IncidentResponse declarerIncident(long employeId, IncidentRequest request) {
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employé introuvable avec id: " + employeId));

        Equipement equipement = equipementRepository.findById(request.getEquipementId())
                .orElseThrow(() -> new ResourceNotFoundException("Équipement introuvable avec id: " + request.getEquipementId()));

        equipement.setStatut(StatutEquipement.EN_PANNE);
        equipementRepository.save(equipement);

        Incident incident = incidentMapper.toEntity(request);
        incident.setDeclarePar(employe);
        incident.setEquipement(equipement);
        incident.setStatut(StatutIncident.OUVERT);
        incident.setDateDeclaration(LocalDateTime.now());

        return incidentMapper.toDTO(incidentRepository.save(incident));
    }

    @Override
    @Transactional
    public IncidentResponse assignerTechnicien(long incidentId, long technicienId) {
        Incident incident = getIncidentEntity(incidentId);
        Technicien technicien = technicienRepository.findById(technicienId)
                .orElseThrow(() -> new ResourceNotFoundException("Technicien introuvable avec id: " + technicienId));

        incident.setTraitePar(technicien);
        incident.setStatut(StatutIncident.EN_COURS);

        Equipement equipement = incident.getEquipement();
        equipement.setStatut(StatutEquipement.EN_REPARATION);
        equipementRepository.save(equipement);

        return incidentMapper.toDTO(incidentRepository.save(incident));
    }

    @Override
    @Transactional
    public IncidentResponse mettreAJourIncident(long incidentId, IncidentUpdateRequest request) {
        Incident incident = getIncidentEntity(incidentId);

        if (request.getStatut() != null) {
            incident.setStatut(request.getStatut());
        }
        if (request.getRapportIntervention() != null) {
            incident.setRapportIntervention(request.getRapportIntervention());
        }

        Equipement equipement = incident.getEquipement();

        if (incident.getStatut() == StatutIncident.EN_COURS) {
            equipement.setStatut(StatutEquipement.EN_REPARATION);
        } else if (incident.getStatut() == StatutIncident.RESOLU) {
            incident.setDateResolution(LocalDateTime.now());
            equipement.setStatut(StatutEquipement.EN_STOCK);
        }

        equipementRepository.save(equipement);

        return incidentMapper.toDTO(incidentRepository.save(incident));
    }

    @Override
    public Page<IncidentResponse> allIncidents(Pageable pageable) {
        return incidentRepository.findAll(pageable).map(incidentMapper::toDTO);
    }

    @Override
    public Page<IncidentResponse> incidentsParEmploye(long employeId, Pageable pageable) {
        return incidentRepository.findByDeclarePar_Id(employeId, pageable).map(incidentMapper::toDTO);
    }

    @Override
    public Page<IncidentResponse> incidentsParTechnicien(long technicienId, Pageable pageable) {
        return incidentRepository.findByTraitePar_Id(technicienId, pageable).map(incidentMapper::toDTO);
    }

    @Override
    public IncidentResponse chercherById(long id) {
        return incidentMapper.toDTO(getIncidentEntity(id));
    }

    public Incident getIncidentEntity(long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incident introuvable avec id: " + id));
    }
}