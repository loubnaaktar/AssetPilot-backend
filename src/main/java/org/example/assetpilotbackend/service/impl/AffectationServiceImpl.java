package org.example.assetpilotbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.affectation.AffectationRequest;
import org.example.assetpilotbackend.dto.affectation.AffectationResponse;
import org.example.assetpilotbackend.enums.StatutAffectation;
import org.example.assetpilotbackend.enums.StatutEquipement;
import org.example.assetpilotbackend.exception.ResourceNotFoundException;
import org.example.assetpilotbackend.mapper.AffectationMapper;
import org.example.assetpilotbackend.model.Affectation;
import org.example.assetpilotbackend.model.Employe;
import org.example.assetpilotbackend.model.Equipement;
import org.example.assetpilotbackend.repository.AffectationRepository;
import org.example.assetpilotbackend.repository.EmployeRepository;
import org.example.assetpilotbackend.repository.EquipementRepository;
import org.example.assetpilotbackend.service.AffectationService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AffectationServiceImpl implements AffectationService {

    private final AffectationRepository affectationRepository;
    private final EmployeRepository employeRepository;
    private final EquipementRepository equipementRepository;
    private final AffectationMapper affectationMapper;


    @Override
    @CacheEvict(value = "affectations", allEntries = true)
    public AffectationResponse creerAffectation(AffectationRequest request) {
        Employe employe = employeRepository.findById(request.getEmployeId())
                .orElseThrow(() -> new ResourceNotFoundException("Employé introuvable avec id: " + request.getEmployeId()));

        Equipement equipement = equipementRepository.findById(request.getEquipementId())
                .orElseThrow(() -> new ResourceNotFoundException("Équipement introuvable avec id: " + request.getEquipementId()));

        if(equipement.getStatut() != StatutEquipement.EN_STOCK){
            throw new IllegalArgumentException("L'équipement doit être 'EN_STOCK' pour être affecté.");
        }

        boolean possedeDejaEquipementMemeCategorie = employe.getAffectations().stream()
                .filter(a -> a.getStatut() == StatutAffectation.ACTIF)
                .map(Affectation::getEquipement)
                .anyMatch(e -> e.getCategorie().getId().equals(equipement.getCategorie().getId()));

        if (possedeDejaEquipementMemeCategorie) {
            throw new IllegalArgumentException("Cet employé possède déjà un équipement actif dans la catégorie : "
                    + equipement.getCategorie().getNom());
        }

        equipement.setStatut(StatutEquipement.AFFECTE);
        equipementRepository.save(equipement);

        Affectation affectation = new Affectation();
        affectation.setDateDebut(request.getDateDebut());
        affectation.setEmploye(employe);
        affectation.setEquipement(equipement);
        affectation.setStatut(StatutAffectation.ACTIF);

        return affectationMapper.toDTO(affectationRepository.save(affectation));
    }

    @Override
    @CacheEvict(value = "affectations", key = "#affectationId")
    public AffectationResponse restituerEquipement(long affectationId) {
        Affectation affectation = getAffectationEntity(affectationId);
        if(affectation.getStatut() == StatutAffectation.RESTITUE){
            throw new IllegalArgumentException("Cette affectation est déjà restituée.");
        }
        affectation.setStatut(StatutAffectation.RESTITUE);
        affectation.setDateFin(LocalDate.now());

        Equipement equipement = affectation.getEquipement();
        equipement.setStatut(StatutEquipement.EN_STOCK);
        equipementRepository.save(equipement);

        return affectationMapper.toDTO(affectationRepository.save(affectation));
    }

    @Override
    public Page<AffectationResponse> allAffectations(Pageable pageable) {
        return affectationRepository.findAll(pageable).map(affectationMapper::toDTO );
    }

    @Override
    @Cacheable(value = "affectations", key = "#id")
    public AffectationResponse chercherById(long id) {
        return affectationMapper.toDTO(getAffectationEntity(id));
    }

    public Affectation getAffectationEntity(long id) {
        return affectationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Affectation introuvable avec id: " + id));
    }
}
