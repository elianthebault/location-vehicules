package com.accenture.repository.entity.vehicule;

import com.accenture.shared.enumeration.TypeVoiture;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Classe enfant héritant de la classe mère Vehicule et classe intermédiaire QuatreRoues.
 */

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Voiture extends QuatreRoues {
    private Integer nombrePortes;
    private Integer nombreBagages;
    @Enumerated(EnumType.STRING)
    private TypeVoiture typeVoiture;
}
