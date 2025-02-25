package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeMoto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MotoRequestDTO(
        //From Vehicule

        @NotBlank(message = "Une marque doit être renseignée")
        String marque,
        @NotBlank(message = "Un model doit être renseigné")
        String modele,
        @NotBlank(message = "Une couleur doit être renseignée")
        String couleur,
        @NotNull(message = "Un tarif journalier doit être renseigné")
        Double tarif,
        @NotNull(message = "Le kilométrage doit être renseigné")
        Integer kilometrage,
        @NotNull(message = "Le véhicule est inactif par défault (false)")
        Boolean actif,
        @NotNull(message = "Le véhicule n'est pas retiré par défault (false)")
        Boolean retire,

        //DeuxRoues

        @NotNull(message = "Un poids doit être renseigné")
        Integer poids,

        //Moto

        @NotNull(message = "Le nombre de cylindres doit être renseigné")
        Integer nombreCylindres,
        @NotNull(message = "La taille du cylindrée doit être renseignée")
        Integer cylindree,
        @NotNull(message = "La puissance (en kW) doit être renseignée")
        Integer puissanceKW,
        @NotNull(message = "La hauteur de selle doit être renseignée")
        Integer hauteurSelle,
        @NotNull(message = "La transmission doit être renseignée")
        Transmission transmission,
        @NotNull(message = "Le type de la moto doit être renseigné")
        TypeMoto typeMoto
) {
}
