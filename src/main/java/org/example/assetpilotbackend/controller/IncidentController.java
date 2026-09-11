package org.example.assetpilotbackend.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.incident.IncidentRequest;
import org.example.assetpilotbackend.dto.incident.IncidentResponse;
import org.example.assetpilotbackend.dto.incident.IncidentUpdateRequest;
import org.example.assetpilotbackend.service.IncidentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentService incidentService;

    @PostMapping("/declarer/{employeId}")
    public IncidentResponse declarerIncident(@PathVariable long employeId, @Valid @RequestBody IncidentRequest request) {
        return incidentService.declarerIncident(employeId, request);
    }

    @PutMapping("/{incidentId}/assigner/{technicienId}")
    public IncidentResponse assignerTechnicien(@PathVariable long incidentId, @PathVariable long technicienId) {
        return incidentService.assignerTechnicien(incidentId, technicienId);
    }

    @PutMapping("/{incidentId}")
    public IncidentResponse mettreAJourIncident(@PathVariable long incidentId, @RequestBody IncidentUpdateRequest request) {
        return incidentService.mettreAJourIncident(incidentId, request);
    }

    @GetMapping
    public Page<IncidentResponse> allIncidents(Pageable pageable) {
        return incidentService.allIncidents(pageable);
    }

    @GetMapping("/employe/{employeId}")
    public Page<IncidentResponse> incidentsParEmploye(@PathVariable long employeId, Pageable pageable) {
        return incidentService.incidentsParEmploye(employeId, pageable);
    }

    @GetMapping("/technicien/{technicienId}")
    public Page<IncidentResponse> incidentsParTechnicien(@PathVariable long technicienId, Pageable pageable) {
        return incidentService.incidentsParTechnicien(technicienId, pageable);
    }

    @GetMapping("/{id}")
    public IncidentResponse chercherById(@PathVariable long id) {
        return incidentService.chercherById(id);
    }

    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportExcel() {
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                .header("Content-Disposition", "attachment; filename=incidents.xlsx")
                .body(incidentService.exporterExcel());
    }
}