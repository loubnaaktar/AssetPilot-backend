package org.example.assetpilotbackend.auth;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.dto.auth.AuthResponse;
import org.example.assetpilotbackend.dto.auth.LoginRequest;
import org.example.assetpilotbackend.model.Utilisateur;
import org.example.assetpilotbackend.repository.UtilisateurRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurRepository utilisateurRepository;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        Utilisateur utilisateur = utilisateurRepository.findByEmail(request.getEmail());
        if (utilisateur == null) {
            throw new IllegalArgumentException("Utilisateur introuvable avec email: " + request.getEmail());
        }

        String token = jwtService.generateToken(utilisateur);
        return new AuthResponse(token, utilisateur.getEmail(), utilisateur.getRole().name());
    }
}