package com.accenture.repository.entity.vehicule;

import com.accenture.shared.enumeration.TypeUtilitaire;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Utilitaire extends QuatreRoues {
    private Integer chargeMaximale;
    private Double poidsPTAC;
    private Integer capacite;
    @Enumerated(EnumType.STRING)
    private TypeUtilitaire typeUtilitaire;
}
