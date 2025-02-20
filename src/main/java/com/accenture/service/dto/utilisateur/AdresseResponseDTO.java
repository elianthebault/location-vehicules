package com.accenture.service.dto.utilisateur;

public record AdresseResponseDTO(
        int id,
        String adresse,
        String codePostal,
        String ville
) {
}
