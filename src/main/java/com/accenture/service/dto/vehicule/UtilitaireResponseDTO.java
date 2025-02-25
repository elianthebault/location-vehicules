package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCarburant;
import com.accenture.shared.enumeration.TypeUtilitaire;

import java.util.List;

public record UtilitaireResponseDTO(
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
        //Utilitaire
        Integer chargeMaximale,
        Double poidsPTAC,
        Integer capacite,
        TypeUtilitaire typeUtilitaire
) {
}
