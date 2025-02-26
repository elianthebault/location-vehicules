package com.accenture.service.dto.location;

import com.accenture.shared.enumeration.TypeAccessoire;

public record AccessoireResponseDTO(
        int id,
        TypeAccessoire typeAccessoire,
        String libelle,
        Integer montant
) {
}
