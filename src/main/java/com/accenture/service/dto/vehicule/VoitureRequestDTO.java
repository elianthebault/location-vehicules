package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCarburant;
import com.accenture.shared.enumeration.TypeVoiture;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record VoitureRequestDTO(
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
        @NotNull(message = "Le véhicule est inactif par défault")
        Boolean actif,
        @NotNull(message = "Le véhicule n'est pas retiré par défault")
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

        //From Voiture

        @NotNull(message = "Un nombre de portes doit être renseigné")
        Integer nombrePortes,
        @NotNull(message = "Un nombre de bagages doit être renseigné")
        Integer nombreBagages,
        @NotNull(message = "Un type de voiture doit être renseigné")
        TypeVoiture typeVoiture
) {
}
