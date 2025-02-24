package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.TypeMoto;

public record MotoRequestDTO(
        //Vehicule
        String marque,
        String modele,
        String couleur,
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
