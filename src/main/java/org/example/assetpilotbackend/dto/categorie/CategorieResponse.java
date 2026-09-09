package org.example.assetpilotbackend.dto.categorie;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategorieResponse {

    private Long id;
    private String nom;
    private String description;
}
