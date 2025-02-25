package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.TypeVelo;

import java.util.List;

public record VeloResponseDTO(
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
        //DeuxRoues
        Integer poids,
        //Velo
        Integer tailleCadre,
        Boolean electrique,
        Integer autonomieBatterie,
        Boolean freinsADisque,
        TypeVelo typeVelo
) {
}
