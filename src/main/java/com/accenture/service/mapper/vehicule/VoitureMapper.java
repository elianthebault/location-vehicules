package com.accenture.service.mapper.vehicule;

import com.accenture.repository.entity.vehicule.Voiture;
import com.accenture.service.dto.vehicule.VoitureRequestDTO;
import com.accenture.service.dto.vehicule.VoitureResponseDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VoitureMapper {
    Voiture toVoiture(VoitureRequestDTO clientRequestDTO);
    VoitureResponseDTO toVoitureResponseDTO(Voiture voiture);
    Voiture toVoitureListe(VoitureResponseDTO voitureResponseDTO);
}
