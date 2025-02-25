package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.TypeVelo;

import java.util.List;

public record VeloResponseDTO(
        //Vehicule
        int id,
        String marque,
        String modele,
        String couleur,
        List<Permis> listePermis,
        double tarif,
        int kilometrage,
        Boolean actif,
        Boolean retire,
        //DeuxRoues
        int poids,
        //Velo
        int tailleCadre,
        Boolean electrique,
        int autonomieBatterie,
        Boolean freinsADisque,
        TypeVelo typeVelo
) {
}
