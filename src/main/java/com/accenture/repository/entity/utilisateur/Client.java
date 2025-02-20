package com.accenture.repository.entity.utilisateur;

import com.accenture.shared.Permis;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.action.internal.OrphanRemovalAction;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Client extends UtilisateurConnecte {
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Adresse adresse;
    private LocalDate dateNaissance;
    private LocalDate dateInscription = LocalDate.now();
    @Enumerated(EnumType.STRING)
    private List<Permis> listePermis;
    private Boolean desactive = false;
}
