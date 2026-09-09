package org.example.assetpilotbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.employe.EmployeRequest;
import org.example.assetpilotbackend.dto.employe.EmployeResponse;
import org.example.assetpilotbackend.exception.ResourceNotFoundException;
import org.example.assetpilotbackend.mapper.EmployeMapper;
import org.example.assetpilotbackend.model.Employe;
import org.example.assetpilotbackend.repository.EmployeRepository;
import org.example.assetpilotbackend.service.EmployeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeServiceImpl implements EmployeService {

    private final EmployeRepository repo;
    private final EmployeMapper mapper;

    @Override
    public EmployeResponse ajouterEmploye(EmployeRequest request) {
        Employe employe = repo.save(mapper.toEntity(request));
        return mapper.toDTO(employe);
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
    public EmployeResponse modifierEmploye(long id, EmployeRequest request) {
        Employe employe = getEmployeEntity(id);
        employe.setNom(request.getNom());
        employe.setPrenom(request.getPrenom());
        employe.setEmail(request.getEmail());
        employe.setMatricule(request.getMatricule());

        return mapper.toDTO(repo.save(employe));
    }

    @Override
    public void supprimerEmploye(long id) {
        repo.delete(getEmployeEntity(id));
    }

    private Employe getEmployeEntity(Long id){
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employé introuvable avec id: " + id));
    }
}
