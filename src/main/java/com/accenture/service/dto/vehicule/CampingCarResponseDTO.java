package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCampingcar;
import com.accenture.shared.enumeration.TypeCarburant;

public record CampingCarResponseDTO(
        //Vehicule
        int id,
        String marque,
        String modele,
        String couleur,
        Permis permis,
        double tarif,
        int kilometrage,
        Boolean actif,
        Boolean retire,
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
