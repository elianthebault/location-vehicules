package com.accenture.repository.entity.vehicule;

import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCarburant;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public abstract class QuatreRoues extends Vehicule {
    @Enumerated(EnumType.STRING)
    private Transmission transmission;
    @Enumerated(EnumType.STRING)
    private TypeCarburant typeCarburant;
    private Boolean climatisation;
}
