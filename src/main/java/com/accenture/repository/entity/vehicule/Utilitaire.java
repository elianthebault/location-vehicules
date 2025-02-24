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
public class Utilitaire {
    private int chargeMaximale;
    private int poidsPTAC;
    private int capacite;
    @Enumerated(EnumType.STRING)
    private TypeUtilitaire typeUtilitaire;
}
