package com.accenture.repository.entity.vehicule;

import com.accenture.shared.enumeration.TypeVoiture;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Voiture extends QuatreRoues {
    private int nombrePortes;
    private int nombreBagages;
    @Enumerated(EnumType.STRING)
    private TypeVoiture typeVoiture;
}
