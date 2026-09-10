package org.example.assetpilotbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.employe.EmployeRequest;
import org.example.assetpilotbackend.dto.employe.EmployeResponse;
import org.example.assetpilotbackend.service.EmployeService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employes")
@RequiredArgsConstructor
public class EmployeController {
    private final EmployeService employeService;

    @PostMapping
    public EmployeResponse ajouterEmploye(@Valid EmployeRequest request){
        return employeService.ajouterEmploye(request);
    }

    @GetMapping
    public Page<EmployeResponse> getAllEmployes(Pageable pageable){
        return employeService.allEmployes(pageable);
    }

    @GetMapping("/{id}")
    public EmployeResponse chercherParId(@PathVariable Long id){
        return employeService.chercherById(id);
    }

    @PutMapping("/{id}")
    public EmployeResponse modifierEmploye(@PathVariable Long id, @Valid @RequestBody EmployeRequest request){
        return employeService.modifierEmploye(id,request);
    }

    @DeleteMapping("/{id}")
    public void supprimerEmploye(@PathVariable Long id){
        employeService.supprimerEmploye(id);
    }
}
