package org.example.assetpilotbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.employe.EmployeRequest;
import org.example.assetpilotbackend.dto.employe.EmployeResponse;
import org.example.assetpilotbackend.service.EmployeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeController {
    private final EmployeService employeService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeResponse ajouterEmploye(@Valid @RequestBody EmployeRequest request){
        return employeService.ajouterEmploye(request);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Page<EmployeResponse> getAllEmployes(Pageable pageable){
        return employeService.allEmployes(pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeResponse chercherParId(@PathVariable Long id){
        return employeService.chercherById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public EmployeResponse modifierEmploye(@PathVariable Long id, @Valid @RequestBody EmployeRequest request){
        return employeService.modifierEmploye(id,request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void supprimerEmploye(@PathVariable Long id){
        employeService.supprimerEmploye(id);
    }
}
