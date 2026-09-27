package org.example.assetpilotbackend.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendPassword(String email, String password) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Vos identifiants d'accès - AssetPilot");
        message.setText(
                "Bonjour,\n\n" +
                        "Bienvenue sur la plateforme AssetPilot !\n\n" +
                        "Votre mot de passe temporaire est : " + password + "\n\n" +
                        "Nous vous conseillons de le modifier dès votre première connexion.\n\n" +
                        "Cordialement,\n" +
                        "L'équipe AssetPilot"
        );
        mailSender.send(message);
    }
}
