package org.example.assetpilotbackend.service;

import org.example.assetpilotbackend.dto.employe.EmployeRequest;
import org.example.assetpilotbackend.dto.employe.EmployeResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeService {
    EmployeResponse ajouterEmploye(EmployeRequest request);
    Page<EmployeResponse> allEmployes(Pageable pageable);
    EmployeResponse chercherById(long id);
    EmployeResponse modifierEmploye(long id, EmployeRequest request);
    void supprimerEmploye(long id);
}
