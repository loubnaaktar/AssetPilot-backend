package org.example.assetpilotbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.technicien.TechnicienRequest;
import org.example.assetpilotbackend.dto.technicien.TechnicienResponse;
import org.example.assetpilotbackend.enums.Role;
import org.example.assetpilotbackend.exception.ResourceNotFoundException;
import org.example.assetpilotbackend.mapper.TechnicienMapper;
import org.example.assetpilotbackend.model.Technicien;
import org.example.assetpilotbackend.repository.TechnicienRepository;
import org.example.assetpilotbackend.service.TechnicienService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechnicienServiceImpl implements TechnicienService {
    private final TechnicienRepository repo;
    private final TechnicienMapper mapper;

    @Override
    public TechnicienResponse ajouterTechnicien(TechnicienRequest request) {
        Technicien technicien = mapper.toEntity(request);
        technicien.setRole(Role.ROLE_TECHNICIAN);
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
    public TechnicienResponse modifierTechnicien(long id, TechnicienRequest request) {
        Technicien technicien = getTechnicienEntity(id);
        technicien.setNom(request.getNom());
        technicien.setPrenom(request.getPrenom());
        technicien.setEmail(request.getEmail());
        technicien.setSpecialite(request.getSpecialite());
        return mapper.toDTO(repo.save(technicien));
    }

    @Override
    public void supprimerTechnicien(long id) {
        repo.delete(getTechnicienEntity(id));
    }

    private Technicien getTechnicienEntity(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Technicien introuvable avec id: " + id));
    }
}
