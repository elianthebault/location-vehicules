package com.accenture.service.dto.vehicule;

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
        double tarif,
        @NotNull(message = "Le kilométrage doit être renseigné")
        int kilometrage,
        @NotNull(message = "Le véhicule est inactif par défault (false)")
        Boolean actif,
        @NotNull(message = "Le véhicule n'est pas retiré par défault (false)")
        Boolean retire,

        //DeuxRoues

        @NotNull(message = "Un poids doit être renseigné")
        int poids,

        //Moto

        @NotNull(message = "Le nombre de cylindres doit être renseigné")
        int nombreCylindres,
        @NotNull(message = "La taille du cylindrée doit être renseignée")
        int cylindree,
        @NotNull(message = "La puissance (en kW) doit être renseignée")
        int puissanceKW,
        @NotNull(message = "La hauteur de selle doit être renseignée")
        int hauteurSelle,
        @NotNull(message = "Le type de la moto doit être renseigné")
        TypeMoto typeMoto
) {
}
