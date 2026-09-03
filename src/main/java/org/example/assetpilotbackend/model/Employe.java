package org.example.assetpilotbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Employe extends Utilisateur {

    private String matricule;

    @OneToMany(mappedBy = "employe")
    private List<Affectation> affectations = new ArrayList<>();
    @OneToMany(mappedBy = "declarePar")
    private List<Incident> incidentsDeclares = new ArrayList<>();
}