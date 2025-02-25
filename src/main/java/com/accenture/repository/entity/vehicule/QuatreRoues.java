package com.accenture.repository.entity.vehicule;

import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCarburant;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Classe intermédiaire pour les véhicules à 4 roues.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class QuatreRoues extends Vehicule {
    private Integer nombrePlaces;
    @Enumerated(EnumType.STRING)
    private TypeCarburant typeCarburant;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    private Boolean climatisation;
}
