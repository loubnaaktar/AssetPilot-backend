package org.example.assetpilotbackend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.assetpilotbackend.enums.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String prenom;
    private String nom;
    private String email;
    private Role role;
}
