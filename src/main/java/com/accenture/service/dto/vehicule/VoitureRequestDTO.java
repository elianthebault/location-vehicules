package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCarburant;
import com.accenture.shared.enumeration.TypeVoiture;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public record VoitureRequestDTO(
        //Vehicule
        String marque,
        String modele,
        String couleur,
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
