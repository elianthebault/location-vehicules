package com.accenture.repository.entity.utilisateur;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Administrateur extends UtilisateurConnecte {
    private String fonction;
}
