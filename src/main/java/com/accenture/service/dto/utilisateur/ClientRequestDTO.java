package com.accenture.service.dto.utilisateur;

import com.accenture.repository.entity.utilisateur.Adresse;
import com.accenture.shared.Permis;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDate;
import java.util.List;

public record ClientRequestDTO(
        String nom,
        String prenom,
        String email,
        String password,
        Adresse adresse,
        LocalDate dateNaissance,
        LocalDate dateInscription,
        List<Permis> listePermis,
        Boolean desactive
) {
}
