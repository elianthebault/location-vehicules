package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCampingcar;
import com.accenture.shared.enumeration.TypeCarburant;

public record CampingCarRequestDTO(
        //Vehicule
        String marque,
        String modele,
        String couleur,
        //QuatreRoues
        int nombrePlaces,
        TypeCarburant typeCarburant,
        Transmission transmission,
        Boolean climatisation,
        //CampingCar
        int poidsPTAC,
        int hauteur,
        int nombreCouchages,
        Boolean cuisineEquipee,
        Boolean litterie,
        Boolean refrigerateur,
        Boolean douche,
        TypeCampingcar typeCampingcar
) {
}
