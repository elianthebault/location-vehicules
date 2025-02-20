package com.accenture.service.mapper;

import com.accenture.repository.entity.utilisateur.Administrateur;
import com.accenture.service.dto.utilisateur.AdministrateurRequestDTO;
import com.accenture.service.dto.utilisateur.AdministrateurResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AdministrateurMapper {
    Administrateur toAdministrateur(AdministrateurRequestDTO administrateurRequestDTO);
    AdministrateurResponseDTO toAdministrateurResponseDTO(Administrateur administrateur);
}
