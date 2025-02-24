package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCarburant;
import com.accenture.shared.enumeration.TypeVoiture;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VoitureRequestDTO(
        //From Vehicule

        @NotBlank(message = "Une marque doit être renseignée")
        String marque,
        @NotBlank(message = "Un model doit être renseigné")
        String modele,
        @NotBlank(message = "Une couleur doit être renseignée")
        String couleur,

        //From QuatreRoues

        @NotNull(message = "Un nombre de places doit être renseigné")
        int nombrePlaces,
        @NotNull(message = "Un type de carburant doit être renseigné")
        TypeCarburant typeCarburant,
        @NotNull(message = "Un type de transmission doit être renseigné")
        Transmission transmission,
        @NotNull(message = "La climatisation doit être renseignée")
        Boolean climatisation,

        //From Voiture

        @NotNull(message = "Un nombre de portes doit être renseigné")
        int nombrePortes,
        @NotNull(message = "Un nombre de bagages doit être renseigné")
        int nombreBagages,
        @NotNull(message = "Un type de voiture doit être renseigné")
        TypeVoiture typeVoiture
) {
}
