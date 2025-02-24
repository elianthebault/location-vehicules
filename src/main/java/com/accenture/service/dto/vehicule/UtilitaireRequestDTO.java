package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCarburant;
import com.accenture.shared.enumeration.TypeUtilitaire;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record UtilitaireRequestDTO(
        //Vehicule
        String marque,
        String modele,
        String couleur,
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
