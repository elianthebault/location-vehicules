package com.accenture.repository.entity.utilisateur;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Administrateur hérite de UtilisateurConnecte
 * Voir {@Link classesabstraites.UtilisateurConnecte}
 *
 * Elle permet de définir les utilisateurs de type Administrateur
 */

@Data
@NoArgsConstructor
@Entity
public class Administrateur extends UtilisateurConnecte {
    private String fonction;
}
