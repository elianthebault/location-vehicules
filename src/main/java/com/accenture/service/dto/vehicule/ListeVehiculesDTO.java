package com.accenture.service.dto.vehicule;

import java.util.List;

public record ListeVehiculesDTO(
        List<VoitureResponseDTO> listeVoitureResponseDTO,
        List<UtilitaireResponseDTO> listeUtilitaireResponseDTO,
        List<CampingCarResponseDTO> listeCampingCarResponseDTO,
        List<MotoResponseDTO> listeMotoResponseDTO,
        List<VeloResponseDTO> listeVeloResponseDTO
) {
}
