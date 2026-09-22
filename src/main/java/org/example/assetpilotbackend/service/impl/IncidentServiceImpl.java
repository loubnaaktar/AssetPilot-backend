package org.example.assetpilotbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.incident.IncidentRequest;
import org.example.assetpilotbackend.dto.incident.IncidentResponse;
import org.example.assetpilotbackend.dto.incident.IncidentUpdateRequest;
import org.example.assetpilotbackend.enums.NiveauUrgence;
import org.example.assetpilotbackend.enums.StatutAffectation;
import org.example.assetpilotbackend.enums.StatutEquipement;
import org.example.assetpilotbackend.enums.StatutIncident;
import org.example.assetpilotbackend.exception.ResourceNotFoundException;
import org.example.assetpilotbackend.mapper.IncidentMapper;
import org.example.assetpilotbackend.model.Employe;
import org.example.assetpilotbackend.model.Equipement;
import org.example.assetpilotbackend.model.Incident;
import org.example.assetpilotbackend.model.Technicien;
import org.example.assetpilotbackend.repository.AffectationRepository;
import org.example.assetpilotbackend.repository.EmployeRepository;
import org.example.assetpilotbackend.repository.EquipementRepository;
import org.example.assetpilotbackend.repository.IncidentRepository;
import org.example.assetpilotbackend.repository.TechnicienRepository;
import org.example.assetpilotbackend.service.IncidentService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class IncidentServiceImpl implements IncidentService {

    private final IncidentRepository incidentRepository;
    private final EmployeRepository employeRepository;
    private final EquipementRepository equipementRepository;
    private final TechnicienRepository technicienRepository;
    private final AffectationRepository affectationRepository;
    private final IncidentMapper incidentMapper;

    @Override
    @Transactional
    public IncidentResponse declarerIncident(long employeId, IncidentRequest request) {
        Employe employe = employeRepository.findById(employeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employé introuvable avec id: " + employeId));

        Equipement equipement = equipementRepository.findById(request.getEquipementId())
                .orElseThrow(() -> new ResourceNotFoundException("Équipement introuvable avec id: " + request.getEquipementId()));

        if (equipement.getStatut() == StatutEquipement.EN_PANNE
                || equipement.getStatut() == StatutEquipement.EN_REPARATION
                || equipement.getStatut() == StatutEquipement.HORS_SERVICE) {
            throw new IllegalArgumentException("Cet équipement est déjà en cours de traitement.");
        }

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
        passerEnReparation(equipement);
        equipementRepository.save(equipement);

        return incidentMapper.toDTO(incidentRepository.save(incident));
    }

    @Override
    @Transactional
    public IncidentResponse mettreAJourIncident(long incidentId, IncidentUpdateRequest request) {
        Incident incident = getIncidentEntity(incidentId);

        if (incident.getStatut() == StatutIncident.RESOLU) {
            throw new IllegalArgumentException("Cet incident est déjà résolu et ne peut plus être modifié.");
        }

        if (request.getStatut() != null) {
            incident.setStatut(request.getStatut());
        }
        if (request.getRapportIntervention() != null) {
            incident.setRapportIntervention(request.getRapportIntervention());
        }

        Equipement equipement = incident.getEquipement();

        boolean horsService = Boolean.TRUE.equals(request.getEquipementHorsService());

        if (incident.getStatut() == StatutIncident.EN_COURS) {
            if (horsService) {
                equipement.setStatut(StatutEquipement.HORS_SERVICE);
            } else {
                passerEnReparation(equipement);
            }
        } else if (incident.getStatut() == StatutIncident.RESOLU) {
            incident.setDateResolution(LocalDateTime.now());
            if (horsService) {
                equipement.setStatut(StatutEquipement.HORS_SERVICE);
            } else {
                mettreAJourStatutApresResolution(equipement);
            }
        }

        equipementRepository.save(equipement);

        return incidentMapper.toDTO(incidentRepository.save(incident));
    }

    private void passerEnReparation(Equipement equipement) {
        if (equipement.getStatut() != StatutEquipement.EN_PANNE) {
            throw new IllegalArgumentException("L'équipement doit être en panne avant de passer en réparation.");
        }
        equipement.setStatut(StatutEquipement.EN_REPARATION);
    }

    private void mettreAJourStatutApresResolution(Equipement equipement) {
        boolean encoreAffecte = affectationRepository
                .existsByEquipement_IdAndStatut(equipement.getId(), StatutAffectation.ACTIF);
        equipement.setStatut(encoreAffecte ? StatutEquipement.AFFECTE : StatutEquipement.EN_STOCK);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IncidentResponse> allIncidents(String mot, StatutIncident statut, NiveauUrgence niveauUrgence, Boolean nonAssigne, Pageable pageable) {
        return incidentRepository.findAllFiltered(mot, statut, niveauUrgence, nonAssigne, pageable).map(incidentMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IncidentResponse> incidentsParEmploye(long employeId, Pageable pageable) {
        return incidentRepository.findByDeclarePar_Id(employeId, pageable).map(incidentMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<IncidentResponse> incidentsParTechnicien(long technicienId, Pageable pageable) {
        return incidentRepository.findByTraitePar_Id(technicienId, pageable).map(incidentMapper::toDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public IncidentResponse chercherById(long id) {
        return incidentMapper.toDTO(getIncidentEntity(id));
    }

    public Incident getIncidentEntity(long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incident introuvable avec id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public byte[] exporterExcel() {
        try (XSSFWorkbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Incidents");

            String[] colonnes = {"ID", "Description", "Urgence", "Statut", "Date declaration",
                    "Date resolution", "Rapport intervention", "Declare par", "Technicien", "Equipement"};

            Row header = sheet.createRow(0);
            for (int i = 0; i < colonnes.length; i++) {
                header.createCell(i).setCellValue(colonnes[i]);
            }

            int rowIndex = 1;
            for (Incident i : incidentRepository.findAll()) {
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(i.getId());
                row.createCell(1).setCellValue(i.getDescription());
                row.createCell(2).setCellValue(i.getNiveauUrgence() != null ? i.getNiveauUrgence().name() : "");
                row.createCell(3).setCellValue(i.getStatut() != null ? i.getStatut().name() : "");
                row.createCell(4).setCellValue(i.getDateDeclaration() != null ? i.getDateDeclaration().toString() : "");
                row.createCell(5).setCellValue(i.getDateResolution() != null ? i.getDateResolution().toString() : "");
                row.createCell(6).setCellValue(i.getRapportIntervention() != null ? i.getRapportIntervention() : "");
                row.createCell(7).setCellValue(i.getDeclarePar() != null ? i.getDeclarePar().getPrenom() + " " + i.getDeclarePar().getNom() : "");
                row.createCell(8).setCellValue(i.getTraitePar() != null ? i.getTraitePar().getPrenom() + " " + i.getTraitePar().getNom() : "");
                row.createCell(9).setCellValue(i.getEquipement() != null ? i.getEquipement().getNumeroSerie() : "");
            }

            workbook.write(out);
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'export Excel", e);
        }
    }
}