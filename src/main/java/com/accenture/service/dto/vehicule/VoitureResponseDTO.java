package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCarburant;
import com.accenture.shared.enumeration.TypeVoiture;

public record VoitureResponseDTO(
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
        //Voiture
        int nombrePortes,
        int nombreBagages,
        TypeVoiture typeVoiture
) {
}
