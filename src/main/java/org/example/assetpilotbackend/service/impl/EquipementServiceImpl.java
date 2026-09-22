package org.example.assetpilotbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.equipement.EquipementRequest;
import org.example.assetpilotbackend.dto.equipement.EquipementResponse;
import org.example.assetpilotbackend.enums.StatutEquipement;
import org.example.assetpilotbackend.enums.StatutAffectation;
import org.example.assetpilotbackend.exception.ResourceNotFoundException;
import org.example.assetpilotbackend.mapper.CategorieMapper;
import org.example.assetpilotbackend.mapper.EquipementMapper;
import org.example.assetpilotbackend.model.Affectation;
import org.example.assetpilotbackend.model.Categorie;
import org.example.assetpilotbackend.model.Equipement;
import org.example.assetpilotbackend.repository.AffectationRepository;
import org.example.assetpilotbackend.repository.CategorieRepository;
import org.example.assetpilotbackend.repository.EquipementRepository;
import org.example.assetpilotbackend.service.EquipementService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EquipementServiceImpl implements EquipementService {

    private final EquipementRepository repo;
    private final EquipementMapper mapper;
    private final CategorieServiceImpl service;
    private final AffectationRepository affectationRepository;

    @Override
    public Page<EquipementResponse> equipementsActifsEmploye(long employeId, Pageable pageable) {
        Page<Affectation> affectations = affectationRepository
                .findByEmploye_IdAndStatut(employeId, StatutAffectation.ACTIF, pageable);

        return affectations.map(affectation -> {
            EquipementResponse response = mapper.toDto(affectation.getEquipement());
            response.setDateAffectation(affectation.getDateDebut());
            return response;
        });
    }

    @Override
    @Transactional
    public EquipementResponse ajouterEquipement(EquipementRequest request) {
       Equipement equipement = mapper.toEntity(request);
       Categorie categorie = service.getCategorieEntity(request.getCategorieId());
       equipement.setCategorie(categorie);
       return mapper.toDto(repo.save(equipement));
    }

    @Override
    public Page<EquipementResponse> allEquipements(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDto);
    }

    @Override
    public Page<EquipementResponse> rechercherEquipements(String mot, StatutEquipement statut, Long categorieId, Pageable pageable) {
        return repo.rechercher(mot, statut, categorieId, pageable).map(mapper::toDto);
    }

    @Override
    public EquipementResponse chercherById(long id) {
        return mapper.toDto(getEquipementEntity(id));
    }

    @Override
    @Transactional
    public EquipementResponse modifierEquipement(long id, EquipementRequest request) {
        Equipement equipement = getEquipementEntity(id);
        Categorie categorie = service.getCategorieEntity(request.getCategorieId());
        equipement.setNumeroSerie(request.getNumeroSerie());
        equipement.setModele(request.getModele());
        equipement.setMarque(request.getMarque());
        equipement.setDateAchat(request.getDateAchat());
        equipement.setStatut(request.getStatut());
        equipement.setCategorie(categorie);

        return mapper.toDto(repo.save(equipement));
    }

    @Override
    public Page<EquipementResponse> equipementsParStatut(StatutEquipement statut, Pageable pageable) {
       return repo.findByStatut(statut,pageable).map(mapper::toDto);
    }

    @Override
    public Page<EquipementResponse> equipementsParCategorie(long categorieId, Pageable pageable) {
        return repo.findByCategorie_Id(categorieId,pageable).map(mapper::toDto);
    }

    @Override
    @Transactional
    public void supprimerEquipement(long id) {
        repo.delete( getEquipementEntity(id));
    }
    public Equipement getEquipementEntity(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Équipement introuvable avec id: " + id));
    }
}
