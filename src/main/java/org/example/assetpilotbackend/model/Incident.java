package org.example.assetpilotbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.assetpilotbackend.enums.NiveauUrgence;
import org.example.assetpilotbackend.enums.StatutIncident;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    @Enumerated(EnumType.STRING)
    private NiveauUrgence niveauUrgence;

    @Enumerated(EnumType.STRING)
    private StatutIncident statut;

    private LocalDateTime dateDeclaration;
    private LocalDateTime dateResolution;
    private String rapportIntervention;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "declare_par_id", nullable = false)
    private Employe declarePar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "traite_par_id")
    private Technicien traitePar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "equipement_id", nullable = false)
    private Equipement equipement;
}