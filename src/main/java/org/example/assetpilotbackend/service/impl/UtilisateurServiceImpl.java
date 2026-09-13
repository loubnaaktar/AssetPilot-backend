package org.example.assetpilotbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurResponse;
import org.example.assetpilotbackend.enums.Role;
import org.example.assetpilotbackend.exception.ResourceNotFoundException;
import org.example.assetpilotbackend.mapper.UtilisateurMapper;
import org.example.assetpilotbackend.model.Employe;
import org.example.assetpilotbackend.model.Technicien;
import org.example.assetpilotbackend.model.Utilisateur;
import org.example.assetpilotbackend.repository.UtilisateurRepository;
import org.example.assetpilotbackend.service.UtilisateurService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository repo;
    private final UtilisateurMapper mapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @CacheEvict(value = "utilisateurs", allEntries = true)
    public UtilisateurResponse registerUtilisateur(UtilisateurRequest request) {
        Utilisateur utilisateur = creerSelonRole(request);
        return mapper.toDTO(repo.save(utilisateur));
    }

    private Utilisateur creerSelonRole(UtilisateurRequest request) {
        Utilisateur utilisateur;
        if (request.getRole() == Role.ROLE_EMPLOYEE) {
            Employe employe = new Employe();
            employe.setMatricule(request.getMatricule());
            utilisateur = employe;
        } else if (request.getRole() == Role.ROLE_TECHNICIAN) {
            Technicien technicien = new Technicien();
            technicien.setSpecialite(request.getSpecialite());
            utilisateur = technicien;
        } else {
            utilisateur = new Utilisateur();
        }

        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setNom(request.getNom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setRole(request.getRole());
        utilisateur.setPassword(passwordEncoder.encode(request.getPassword()));
        return utilisateur;
    }

    @Override
    @Cacheable(value = "utilisateurs", key = "#id")
    public UtilisateurResponse getUtilisateurById(Long id) {
        return mapper.toDTO(getUtilisateurEntity(id));
    }

    @Override
    public UtilisateurResponse getUtilisateurByEmail(String email) {
        Utilisateur utilisateur = repo.findByEmail(email);
        if (utilisateur == null) {
            throw new ResourceNotFoundException("Utilisateur introuvable avec email: " + email);
        }
        return mapper.toDTO(utilisateur);
    }

    @Override
    public Page<UtilisateurResponse> getAllUtilisateurs(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDTO);
    }

    @Override
    @CacheEvict(value = "utilisateurs", key = "#id")
    public UtilisateurResponse modifierUtilisateur(long id, UtilisateurRequest request) {
        Utilisateur utilisateur = getUtilisateurEntity(id);
        utilisateur.setPrenom(request.getPrenom());
        utilisateur.setNom(request.getNom());
        utilisateur.setEmail(request.getEmail());
        utilisateur.setRole(request.getRole());
        if (request.getPassword() != null && !request.getPassword().isBlank()) {
            utilisateur.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return mapper.toDTO(repo.save(utilisateur));
    }

    @Override
    @CacheEvict(value = "utilisateurs", key = "#id")
    public void supprimerUtilisateur(Long id) {
        repo.delete(getUtilisateurEntity(id));
    }

    private Utilisateur getUtilisateurEntity(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable avec id: " + id));
    }
}
