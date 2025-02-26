package com.accenture.service.dto.location;

import com.accenture.repository.entity.location.Accessoire;
import com.accenture.repository.entity.utilisateur.Client;
import com.accenture.repository.entity.vehicule.Vehicule;
import com.accenture.service.dto.utilisateur.ClientResponseDTO;
import com.accenture.shared.enumeration.EtatLocation;

import java.time.LocalDate;
import java.util.List;

public record LocationResponseDTO(
        int id,
        ClientResponseDTO client,
        Vehicule vehicule,
        List<AccessoireResponseDTO> listeAccessoires,
        LocalDate dateDebut,
        LocalDate dateFin,
        Integer kilometresParcourus,
        Double tarifTotal,
        LocalDate daveValidation,
        EtatLocation etatLocation
) {
}
