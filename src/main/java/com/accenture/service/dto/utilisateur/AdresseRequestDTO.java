package com.accenture.service.dto.utilisateur;

public record AdresseRequestDTO(
        String adresse,
        String codePostal,
        String ville
) {
}
