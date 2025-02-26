package com.accenture.repository.entity.vehicule;

import com.accenture.shared.enumeration.TypeCampingcar;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class CampingCar extends QuatreRoues {
    private Integer poidsPTAC;
    private Integer hauteur;
    private Integer nombreCouchages;
    private Boolean cuisineEquipee;
    private Boolean litterie;
    private Boolean refrigerateur;
    private Boolean douche;
    private TypeCampingcar type;
}
