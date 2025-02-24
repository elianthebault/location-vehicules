package com.accenture.repository.entity.vehicule;

import com.accenture.shared.enumeration.Permis;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity
public abstract class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private String marque;
    private String modele;
    private String couleur;
    @Enumerated(EnumType.STRING)
    private Permis permis;
    private Double tarif;
    private Integer kilometrage;
    private Boolean actif;
    private Boolean retire;
}
