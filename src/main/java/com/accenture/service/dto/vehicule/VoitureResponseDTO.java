package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCarburant;
import com.accenture.shared.enumeration.TypeVoiture;

import java.util.List;

public record VoitureResponseDTO(
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
        //Voiture
        Integer nombrePortes,
        Integer nombreBagages,
        TypeVoiture type
) {
}
