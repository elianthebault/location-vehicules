package com.accenture.repository.entity.vehicule;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Classe intermédiaire pour les véhicules à 2 roues.
 */

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class DeuxRoues extends Vehicule {
    private Integer poids;
}
