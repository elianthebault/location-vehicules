package com.accenture.service.mapper;

import com.accenture.repository.entity.utilisateur.Client;
import com.accenture.service.dto.utilisateur.ClientRequestDTO;
import com.accenture.service.dto.utilisateur.ClientResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = AdresseMapper.class)
public interface ClientMapper {
    Client toClient(ClientRequestDTO clientRequestDTO);
    ClientResponseDTO toClientResponseDTO(Client client);
}
