package org.example.assetpilotbackend.mapper;

import org.example.assetpilotbackend.dto.auth.RegisterRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurRequest;
import org.example.assetpilotbackend.dto.utilisateur.UtilisateurResponse;
import org.example.assetpilotbackend.model.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapStructConfig.class)
public interface UtilisateurMapper {

    UtilisateurResponse toDto(Utilisateur utilisateur);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    void updateEntity(UtilisateurRequest request, @MappingTarget Utilisateur utilisateur);

    @Mapping(target = "id", ignore = true)
    void updateEntity(RegisterRequest request, @MappingTarget Utilisateur utilisateur);
}
