package com.accenture.repository.entity.vehicule;

import com.accenture.shared.enumeration.TypeMoto;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Moto extends DeuxRoues {
    private int nombreCylindres;
    private int cylindree;
    private int puissanceKW;
    private int hauteurSelle;
    private TypeMoto typeMoto;
}
