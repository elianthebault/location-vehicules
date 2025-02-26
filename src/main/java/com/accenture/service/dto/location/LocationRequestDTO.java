package com.accenture.service.dto.location;

import com.accenture.repository.entity.location.Accessoire;
import com.accenture.repository.entity.utilisateur.Client;
import com.accenture.repository.entity.vehicule.Vehicule;
import com.accenture.service.dto.utilisateur.ClientRequestDTO;
import com.accenture.shared.enumeration.EtatLocation;

import java.time.LocalDate;
import java.util.List;

public record LocationRequestDTO(
        ClientRequestDTO client,
        Integer vehiculeId,
        List<AccessoireRequestDTO> listeAccessoires,
        LocalDate dateDebut,
        LocalDate dateFin,
        Integer kilometresParcourus
) {
}
