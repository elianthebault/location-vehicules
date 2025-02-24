package com.accenture.service.mapper.utilisateur;

import com.accenture.repository.entity.utilisateur.Adresse;
import com.accenture.service.dto.utilisateur.AdresseRequestDTO;
import com.accenture.service.dto.utilisateur.AdresseResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdresseMapper {
    Adresse toAdresse(AdresseRequestDTO adresseRequestDTO);
    AdresseResponseDTO toAdresseResponseDTO(Adresse adresse);
}
