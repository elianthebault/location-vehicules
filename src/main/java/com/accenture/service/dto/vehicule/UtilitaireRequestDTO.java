package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCarburant;
import com.accenture.shared.enumeration.TypeUtilitaire;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UtilitaireRequestDTO(
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

        //From QuatreRoues

        @NotNull(message = "Un nombre de places doit être renseigné")
        Integer nombrePlaces,
        @NotNull(message = "Un type de carburant doit être renseigné")
        TypeCarburant typeCarburant,
        @NotNull(message = "Un type de transmission doit être renseigné")
        Transmission transmission,
        @NotNull(message = "La climatisation doit être renseignée")
        Boolean climatisation,

        //Utilitaire

        @NotNull(message = "Une charge maximale doit être renseignée")
        Integer chargeMaximale,
        @NotNull(message = "Un poids(PTAC) doit être renseigné")
        Double poidsPTAC,
        @NotNull(message = "la capacité doit être renseignée")
        Integer capacite,
        @NotNull(message = "Un type d'utilitaire doit être renseigné")
        TypeUtilitaire typeUtilitaire
) {
}
