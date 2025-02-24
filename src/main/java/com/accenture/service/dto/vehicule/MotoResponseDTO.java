package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.TypeMoto;

public record MotoResponseDTO(
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
        //DeuxRoues
        int poids,
        //Moto
        int nombreCylindres,
        int cylindree,
        int puissanceKW,
        int hauteurSelle,
        TypeMoto typeMoto
) {
}
