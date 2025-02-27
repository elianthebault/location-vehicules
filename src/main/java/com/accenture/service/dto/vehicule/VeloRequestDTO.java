package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.TypeMoto;
import com.accenture.shared.enumeration.TypeVelo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VeloRequestDTO(
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

        //Velo

        @NotNull(message = "La taille du cadre doit être renseignée")
        int tailleCadre,
        @NotNull(message = "Une motricité électrique doit être renseignée")
        Boolean electrique,
        int autonomieBatterie,
        @NotNull(message = "La présence de freins à disaue doit être renseignéee")
        Boolean freinsADisque,
        @NotNull(message = "Le type du vélo doit être renseigné")
        TypeVelo typeVelo
) {
}
