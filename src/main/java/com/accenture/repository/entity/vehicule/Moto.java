package com.accenture.repository.entity.vehicule;

import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeMoto;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Moto extends DeuxRoues {
    private Integer nombreCylindres;
    private Integer cylindree;
    private Integer puissanceKW;
    private Integer hauteurSelle;
    private Transmission transmission;
    private TypeMoto type;
}
