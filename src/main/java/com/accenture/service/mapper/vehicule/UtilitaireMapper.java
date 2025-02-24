package com.accenture.service.mapper.vehicule;

import com.accenture.repository.entity.vehicule.Utilitaire;
import com.accenture.service.dto.vehicule.UtilitaireRequestDTO;
import com.accenture.service.dto.vehicule.UtilitaireResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UtilitaireMapper {
    Utilitaire toUtilitaire(UtilitaireRequestDTO clientRequestDTO);
    UtilitaireResponseDTO toUtilitaireResponseDTO(Utilitaire utilitaire);
}
