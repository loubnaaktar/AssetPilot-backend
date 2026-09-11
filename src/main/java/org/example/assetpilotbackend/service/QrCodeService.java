package org.example.assetpilotbackend.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import lombok.RequiredArgsConstructor;
import org.example.assetpilotbackend.exception.ResourceNotFoundException;
import org.example.assetpilotbackend.model.Equipement;
import org.example.assetpilotbackend.model.Incident;
import org.example.assetpilotbackend.repository.EquipementRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;

@Service
@RequiredArgsConstructor
public class QrCodeService {

    private final EquipementRepository equipementRepository;

    @Transactional(readOnly = true)
    public byte[] genererQrCode(Long equipementId) {
        Equipement equipement = equipementRepository.findById(equipementId)
                .orElseThrow(() -> new ResourceNotFoundException("Equipement introuvable avec id: " + equipementId));

        StringBuilder contenu = new StringBuilder()
                .append("Equipement ID: ").append(equipement.getId()).append("\n")
                .append("Numero serie: ").append(equipement.getNumeroSerie()).append("\n")
                .append("Marque: ").append(equipement.getMarque()).append("\n")
                .append("Modele: ").append(equipement.getModele()).append("\n")
                .append("Date achat: ").append(equipement.getDateAchat()).append("\n")
                .append("Statut: ").append(equipement.getStatut()).append("\n")
                .append("Categorie: ").append(equipement.getCategorie().getNom());

        if (!equipement.getIncidents().isEmpty()) {
            contenu.append("\n\nIncidents:\n");
            for (Incident i : equipement.getIncidents()) {
                contenu.append("- ").append(i.getDescription()).append("\n")
                        .append("  Statut: ").append(i.getStatut())
                        .append(" | Urgence: ").append(i.getNiveauUrgence())
                        .append(" | Date: ").append(i.getDateDeclaration()).append("\n");
            }
        }

        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(contenu.toString(), BarcodeFormat.QR_CODE, 300, 300);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", out);
            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du QR code", e);
        }
    }
}