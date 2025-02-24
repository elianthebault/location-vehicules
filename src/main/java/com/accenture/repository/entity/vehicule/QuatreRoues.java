package com.accenture.repository.entity.vehicule;

import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCarburant;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public abstract class QuatreRoues extends Vehicule {
    private int nombrePlaces;
    @Enumerated(EnumType.STRING)
    private TypeCarburant typeCarburant;
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    private Boolean climatisation;
}
