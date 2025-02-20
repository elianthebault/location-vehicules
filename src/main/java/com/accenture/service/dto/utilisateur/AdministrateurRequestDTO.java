package com.accenture.service.dto.utilisateur;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdministrateurRequestDTO(
        @NotBlank(message = "Un nom doit être renseigné")
        String nom,
        @NotBlank(message = "Un prénom doit être renseigné")
        String prenom,
        @Email(message = "Un email valide doit être renseigné")
        String email,
        @NotBlank(message = "Un mot de passe doit être renseigné")
        String password,
        @NotBlank(message = "Une fonction doit être renseigné")
        String fonction
) {
}
