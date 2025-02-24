package com.accenture.repository.entity.vehicule;

import com.accenture.shared.enumeration.TypeCampingcar;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class CampingCar {
    private int poidsPTAC;
    private int hauteur;
    private int nombreCouchages;
    private Boolean cuisineEquipee;
    private Boolean litterie;
    private Boolean refrigerateur;
    private Boolean douche;
    private TypeCampingcar typeCampingcar;
}
