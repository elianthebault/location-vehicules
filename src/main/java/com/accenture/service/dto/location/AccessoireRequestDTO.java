package com.accenture.service.dto.location;

import com.accenture.shared.enumeration.TypeAccessoire;

public record AccessoireRequestDTO(
        TypeAccessoire typeAccessoire,
        String libelle,
        Integer montant
) {
}
