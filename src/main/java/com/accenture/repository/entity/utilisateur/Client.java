package com.accenture.repository.entity.utilisateur;

import com.accenture.shared.Permis;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * Client hérite de UtilisateurConnecte
 * Voir {@Link classesabstraites.UtilisateurConnecte}
 *
 * Elle permet de définir les utilisateurs de type Client
 */

@Data
@NoArgsConstructor
@Entity
public class Client extends UtilisateurConnecte {
    /**
     * Adresse est définie dans la class Adresse
     * Voir {@Link classes.Adresse}
     */
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Adresse adresse;
    private LocalDate dateNaissance;
    private LocalDate dateInscription = LocalDate.now();
    /**
     * listePermis est une liste de Permis
     * Voir {@Link énumérations.Permis}
     */
    @Enumerated(EnumType.STRING)
    private List<Permis> listePermis;
    private Boolean desactive = false;
}
