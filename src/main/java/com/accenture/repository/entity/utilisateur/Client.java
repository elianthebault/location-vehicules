package com.accenture.repository.entity.utilisateur;

import com.accenture.shared.Permis;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Client extends UtilisateurConnecte{
    @OneToOne
    private Adresse adresse;
    private LocalDate dateNaissance;
    private LocalDate dateInscription;
    @Enumerated(EnumType.STRING)
    private List<Permis> listePermis;
}
