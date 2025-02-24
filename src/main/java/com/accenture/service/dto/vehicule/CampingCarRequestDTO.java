package com.accenture.service.dto.vehicule;

import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCampingcar;
import com.accenture.shared.enumeration.TypeCarburant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CampingCarRequestDTO(
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

        //From QuatreRoues

        @NotNull(message = "Un nombre de places doit être renseigné")
        int nombrePlaces,
        @NotNull(message = "Un type de carburant doit être renseigné")
        TypeCarburant typeCarburant,
        @NotNull(message = "Un type de transmission doit être renseigné")
        Transmission transmission,
        @NotNull(message = "La climatisation doit être renseignée")
        Boolean climatisation,

        //From CampingCar

        @NotNull(message = "Un poids(PTAC) doit être renseigné")
        int poidsPTAC,
        @NotNull(message = "Une hauteur doit être renseignée")
        int hauteur,
        @NotNull(message = "Un nombre de couchages doit être renseigné")
        int nombreCouchages,
        @NotNull(message = "La présence d'une cuisine équipée doit être renseignée")
        Boolean cuisineEquipee,
        @NotNull(message = "La présence de litterie doit être renseignée")
        Boolean litterie,
        @NotNull(message = "La présence d'un réfrigérateur doit être renseignée")
        Boolean refrigerateur,
        @NotNull(message = "La présence d'une douche doit être renseignée")
        Boolean douche,
        @NotNull(message = "Un type de camping-car doit être renseigné")
        TypeCampingcar typeCampingcar
) {
}
