package com.accenture.repository.entity.location;

import com.accenture.shared.enumeration.TypeAccessoire;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Accessoire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private TypeAccessoire typeAccessoire;
    private String libelle;
    private Integer montant;
}
