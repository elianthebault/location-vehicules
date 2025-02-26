package com.accenture.repository.entity.vehicule;

import com.accenture.shared.enumeration.TypeVelo;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Velo extends DeuxRoues {
    private Integer tailleCadre;
    private Boolean electrique;
    private Integer autonomieBatterie;
    private Boolean freinsADisque;
    private TypeVelo type;
}
