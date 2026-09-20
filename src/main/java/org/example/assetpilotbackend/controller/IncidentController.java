package org.example.assetpilotbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.incident.IncidentRequest;
import org.example.assetpilotbackend.dto.incident.IncidentResponse;
import org.example.assetpilotbackend.dto.incident.IncidentUpdateRequest;
import org.example.assetpilotbackend.enums.NiveauUrgence;
import org.example.assetpilotbackend.enums.StatutIncident;
import org.example.assetpilotbackend.service.IncidentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @PostMapping("/declarer/{employeId}")
    @PreAuthorize("hasRole('EMPLOYE')")
    public IncidentResponse declarerIncident(@PathVariable long employeId, @Valid @RequestBody IncidentRequest request) {
        return incidentService.declarerIncident(employeId, request);
    }

    @PutMapping("/{incidentId}/assigner/{technicienId}")
    @PreAuthorize("hasRole('ADMIN')")
    public IncidentResponse assignerTechnicien(@PathVariable long incidentId, @PathVariable long technicienId) {
        return incidentService.assignerTechnicien(incidentId, technicienId);
    }

    @PutMapping("/{incidentId}")
    @PreAuthorize("hasRole('TECHNICIEN')")
    public IncidentResponse mettreAJourIncident(@PathVariable long incidentId, @RequestBody IncidentUpdateRequest request) {
        return incidentService.mettreAJourIncident(incidentId, request);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIEN')")
    public Page<IncidentResponse> allIncidents(
            @RequestParam(required = false) String mot,
            @RequestParam(required = false) StatutIncident statut,
            @RequestParam(required = false) NiveauUrgence niveauUrgence,
            @RequestParam(required = false) Boolean nonAssigne,
            Pageable pageable) {
        return incidentService.allIncidents(mot, statut, niveauUrgence, nonAssigne, pageable);
    }

    @GetMapping("/employe/{employeId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYE')")
    public Page<IncidentResponse> incidentsParEmploye(@PathVariable long employeId, Pageable pageable) {
        return incidentService.incidentsParEmploye(employeId, pageable);
    }

    @GetMapping("/technicien/{technicienId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIEN')")
    public Page<IncidentResponse> incidentsParTechnicien(@PathVariable long technicienId, Pageable pageable) {
        return incidentService.incidentsParTechnicien(technicienId, pageable);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECHNICIEN')")
    public IncidentResponse chercherById(@PathVariable long id) {
        return incidentService.chercherById(id);
    }

    @GetMapping("/excel")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<byte[]> exportExcel() {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header("Content-Disposition", "attachment; filename=incidents.xlsx")
                .body(incidentService.exporterExcel());
    }
}