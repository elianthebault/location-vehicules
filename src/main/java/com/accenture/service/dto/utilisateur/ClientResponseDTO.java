package com.accenture.service.dto.utilisateur;

import com.accenture.shared.enumeration.Permis;

import java.time.LocalDate;
import java.util.List;

public record ClientResponseDTO(
        int id,
        String nom,
        String prenom,
        String email,
        String password,
        String role,
        AdresseResponseDTO adresse,
        LocalDate dateNaissance,
        LocalDate dateInscription,
        List<Permis> listePermis,
        Boolean desactive
) {
}
