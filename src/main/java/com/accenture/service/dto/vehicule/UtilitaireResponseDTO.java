package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCarburant;
import com.accenture.shared.enumeration.TypeUtilitaire;

public record UtilitaireResponseDTO(
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
        //Utilitaire
        int chargeMaximale,
        int poidsPTAC,
        int capacite,
        TypeUtilitaire typeUtilitaire
) {
}
