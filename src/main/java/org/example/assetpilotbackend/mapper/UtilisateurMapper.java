package org.example.assetpilotbackend.mapper;

import org.example.assetpilotbackend.dto.auth.RegisterRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurResponse;
import org.example.assetpilotbackend.model.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    UtilisateurResponse toDTO(Utilisateur utilisateur);
    Utilisateur toEntity(UtilisateurRequest utilisateurRequest);
}