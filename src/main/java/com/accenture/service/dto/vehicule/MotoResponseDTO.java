package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeMoto;

import java.util.List;

public record MotoResponseDTO(
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
        //Moto
        Integer nombreCylindres,
        Integer cylindree,
        Integer puissanceKW,
        Integer hauteurSelle,
        Transmission transmission,
        TypeMoto typeMoto
) {
}
