package org.example.assetpilotbackend.dto.utilisateur;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.assetpilotbackend.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurRequest {

    @NotBlank(message = "Le prenom est obligatoire")
    private String prenom;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "L'email n'est pas valide")
    private String email;

    @NotNull(message = "Le role est obligatoire")
    private Role role;
}
