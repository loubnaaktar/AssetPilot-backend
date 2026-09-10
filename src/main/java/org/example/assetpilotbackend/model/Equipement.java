package org.example.assetpilotbackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.assetpilotbackend.enums.StatutEquipement;

import java.lang.reflect.GenericArrayType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String NumeroSerie;
    private String modele;
    private String Marque;
    private LocalDate dateAchat;

    @Enumerated(EnumType.STRING)
    private StatutEquipement statut;

    @ManyToOne
    @JoinColumn(name = "categorie_id", nullable = false)
    private Categorie categorie;

    @OneToMany(mappedBy = "equipement")
    private List<Affectation> affectations = new ArrayList<>();

    @OneToMany(mappedBy = "equipement")
    private List<Incident> incidents = new ArrayList<>();

}