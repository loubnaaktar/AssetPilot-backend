package org.example.assetpilotbackend.dto.technicien;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TechnicienDisponible {

    private Long id;
    private String nom;
    private String prenom;
    private String specialite;
    private long incidentsNonResolus;
}