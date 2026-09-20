package org.example.assetpilotbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final PasswordGeneratorService passwordGeneratorService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public String encoderEtEnvoyer(String email) {
        String motDePasseGenere = passwordGeneratorService.generateRawPassword();
        emailService.sendPassword(email, motDePasseGenere);
        return passwordEncoder.encode(motDePasseGenere);
    }
}