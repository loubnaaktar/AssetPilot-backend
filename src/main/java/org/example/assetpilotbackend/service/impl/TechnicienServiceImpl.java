package org.example.assetpilotbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.technicien.TechnicienDisponible;
import org.example.assetpilotbackend.dto.technicien.TechnicienRequest;
import org.example.assetpilotbackend.dto.technicien.TechnicienResponse;
import org.example.assetpilotbackend.enums.Role;
import org.example.assetpilotbackend.enums.StatutIncident;
import org.example.assetpilotbackend.exception.ResourceNotFoundException;
import org.example.assetpilotbackend.mapper.TechnicienMapper;
import org.example.assetpilotbackend.model.Technicien;
import org.example.assetpilotbackend.repository.IncidentRepository;
import org.example.assetpilotbackend.repository.TechnicienRepository;
import org.example.assetpilotbackend.service.AccountService;
import org.example.assetpilotbackend.service.TechnicienService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnicienServiceImpl implements TechnicienService {
    private final TechnicienRepository repo;
    private final IncidentRepository incidentRepository;
    private final TechnicienMapper mapper;
    private final AccountService accountService;

    @Override
    @Transactional
    public TechnicienResponse ajouterTechnicien(TechnicienRequest request) {
        Technicien technicien = mapper.toEntity(request);
        technicien.setRole(Role.TECHNICIEN);
        technicien.setPassword(accountService.encoderEtEnvoyer(technicien.getEmail()));
        return mapper.toDTO(repo.save(technicien));
    }

    @Override
    public Page<TechnicienResponse> allTechniciens(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDTO);
    }

    @Override
    public TechnicienResponse chercherById(long id) {
        return mapper.toDTO(getTechnicienEntity(id));
    }

    @Override
    @Transactional
    public TechnicienResponse modifierTechnicien(long id, TechnicienRequest request) {
        Technicien technicien = getTechnicienEntity(id);
        technicien.setNom(request.getNom());
        technicien.setPrenom(request.getPrenom());
        technicien.setEmail(request.getEmail());
        technicien.setSpecialite(request.getSpecialite());
        return mapper.toDTO(repo.save(technicien));
    }

    @Override
    @Transactional
    public void supprimerTechnicien(long id) {
        repo.delete(getTechnicienEntity(id));
    }

    @Override
    public List<String> allSpecialites() {
        return repo.findDistinctSpecialites();
    }

    @Override
    public List<TechnicienDisponible> techniciensParSpecialite(String specialite) {
        List<TechnicienDisponible> result = new ArrayList<>();
        for (Technicien tech : repo.findBySpecialiteIgnoreCase(specialite)) {
            result.add(new TechnicienDisponible(
                    tech.getId(),
                    tech.getNom(),
                    tech.getPrenom(),
                    tech.getSpecialite(),
                    incidentRepository.countByTraitePar_IdAndStatutNot(tech.getId(), StatutIncident.RESOLU)
            ));
        }
        return result;
    }

    private Technicien getTechnicienEntity(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Technicien introuvable avec id: " + id));
    }
}
