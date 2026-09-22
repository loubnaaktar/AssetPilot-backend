package org.example.assetpilotbackend.dto.utilisateur;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.assetpilotbackend.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfilResponse {

    private Long id;
    private String prenom;
    private String nom;
    private String email;
    private Role role;
    private String matricule;
    private String specialite;
}