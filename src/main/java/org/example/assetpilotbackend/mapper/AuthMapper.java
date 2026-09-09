package org.example.assetpilotbackend.mapper;

import org.example.assetpilotbackend.dto.auth.AuthResponse;
import org.example.assetpilotbackend.model.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    @Mapping(source = "token", target = "token")
    @Mapping(source = "utilisateur.email", target = "email")
    @Mapping(source = "utilisateur.role", target = "role")
    AuthResponse toDto(String token, Utilisateur utilisateur);
}
