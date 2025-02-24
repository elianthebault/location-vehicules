package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.TypeMoto;
import com.accenture.shared.enumeration.TypeVelo;

public record VeloRequestDTO(
        //Vehicule
        String marque,
        String modele,
        String couleur,
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
