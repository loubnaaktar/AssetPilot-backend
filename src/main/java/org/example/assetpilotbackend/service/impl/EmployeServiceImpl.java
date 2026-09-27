package org.example.assetpilotbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.employe.EmployeRequest;
import org.example.assetpilotbackend.dto.employe.EmployeResponse;
import org.example.assetpilotbackend.enums.Role;
import org.example.assetpilotbackend.exception.ResourceNotFoundException;
import org.example.assetpilotbackend.mapper.EmployeMapper;
import org.example.assetpilotbackend.model.Affectation;
import org.example.assetpilotbackend.model.Employe;
import org.example.assetpilotbackend.model.Incident;
import org.example.assetpilotbackend.repository.AffectationRepository;
import org.example.assetpilotbackend.repository.EmployeRepository;
import org.example.assetpilotbackend.repository.IncidentRepository;
import org.example.assetpilotbackend.service.AccountService;
import org.example.assetpilotbackend.service.EmployeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepository repo;
    private final EmployeMapper mapper;
    private final AccountService accountService;
    private final IncidentRepository incidentRepository;
    private final AffectationRepository affectationRepository;

    @Override
    @Transactional
    public EmployeResponse ajouterEmploye(EmployeRequest request) {
        Employe employe = mapper.toEntity(request);
        employe.setRole(Role.EMPLOYE);
        employe.setMatricule("EMP-" + String.format("%03d", repo.count() + 1));
        employe.setPassword(accountService.encoderEtEnvoyer(employe.getEmail()));
        return mapper.toDTO(repo.save(employe));
    }

    @Override
    public Page<EmployeResponse> allEmployes(Pageable pageable) {
        return repo.findAll(pageable).map(mapper::toDTO);
    }

    @Override
    public EmployeResponse chercherById(long id) {
        return mapper.toDTO(getEmployeEntity(id));
    }

    @Override
    @Transactional
    public EmployeResponse modifierEmploye(long id, EmployeRequest request) {
        Employe employe = getEmployeEntity(id);
        employe.setNom(request.getNom());
        employe.setPrenom(request.getPrenom());
        employe.setEmail(request.getEmail());
        if (request.getMatricule() != null) {
            employe.setMatricule(request.getMatricule());
        }

        return mapper.toDTO(repo.save(employe));
    }

    @Override
    @Transactional
    public void supprimerEmploye(long id) {
        Employe employe = getEmployeEntity(id);

        String nomEmploye = employe.getNom();

        List<Incident> incidents = incidentRepository.findByDeclarePar_Id(id);
        for (Incident incident : incidents) {
            incident.setDeclareParNom(nomEmploye);
            incident.setDeclarePar(null);
            incidentRepository.save(incident);
        }

        List<Affectation> affectations = affectationRepository.findByEmploye_Id(id);
        for (Affectation affectation : affectations) {
            affectation.setEmployeNom(nomEmploye);
            affectation.setEmploye(null);
            affectationRepository.save(affectation);
        }

        repo.delete(employe);
    }

    private Employe getEmployeEntity(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employé introuvable avec id: " + id));
    }
}
