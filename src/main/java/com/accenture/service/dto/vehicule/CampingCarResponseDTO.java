package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCampingcar;
import com.accenture.shared.enumeration.TypeCarburant;

import java.util.List;

public record CampingCarResponseDTO(
        //Vehicule
        Integer id,
        String marque,
        String modele,
        String couleur,
        List<Permis> listePermis,
        Double tarif,
        Integer kilometrage,
        Boolean actif,
        Boolean retire,
        //QuatreRoues
        Integer nombrePlaces,
        TypeCarburant typeCarburant,
        Transmission transmission,
        Boolean climatisation,
        //CampingCar
        Integer poidsPTAC,
        Integer hauteur,
        Integer nombreCouchages,
        Boolean cuisineEquipee,
        Boolean litterie,
        Boolean refrigerateur,
        Boolean douche,
        TypeCampingcar typeCampingcar
) {
}
