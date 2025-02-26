package com.accenture.repository.entity.location;

import com.accenture.repository.entity.utilisateur.Client;
import com.accenture.repository.entity.vehicule.Vehicule;
import com.accenture.shared.enumeration.EtatLocation;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Vehicule vehicule;
    @ManyToMany
    private List<Accessoire> listeAccessoires;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private Integer kilometresParcourus;
    private Double tarifTotal;
    private LocalDate daveValidation;
    @Enumerated(EnumType.STRING)
    private EtatLocation etatLocation;
}
