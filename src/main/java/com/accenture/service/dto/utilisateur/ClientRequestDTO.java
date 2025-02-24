package com.accenture.service.dto.utilisateur;

import com.accenture.shared.enumeration.Permis;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public record ClientRequestDTO(
        @NotBlank(message = "Un nom doit être renseigné")
        String nom,
        @NotBlank(message = "Un prénom doit être renseigné")
        String prenom,
        @Email(message = "Un email valide doit être renseigné")
        String email,
        @NotBlank(message = "Un mot de passe doit être renseigné")
        String password,
        @NotNull(message = "Une adresse doit être renseignée")
        AdresseRequestDTO adresse,
        @NotNull(message = "Une date de naissance valide doit être renseignée")
        @Past
        LocalDate dateNaissance,
        List<Permis> listePermis
) {
}
