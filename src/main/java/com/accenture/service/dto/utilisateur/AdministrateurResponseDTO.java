package com.accenture.service.dto.utilisateur;

public record AdministrateurResponseDTO(
        int id,
        String nom,
        String prenom,
        String email,
        String password,
        String role,
        String fonction
) {
}
