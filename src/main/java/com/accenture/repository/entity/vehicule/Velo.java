package com.accenture.repository.entity.vehicule;

import com.accenture.shared.enumeration.TypeVelo;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Velo extends DeuxRoues {
    private int tailleCadre;
    private Boolean electrique;
    private int autonomieBatterie;
    private Boolean freinsADisque;
    private TypeVelo typeVelo;
}
