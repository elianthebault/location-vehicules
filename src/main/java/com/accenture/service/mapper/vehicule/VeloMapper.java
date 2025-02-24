package com.accenture.service.mapper.vehicule;

import com.accenture.repository.entity.vehicule.Velo;
import com.accenture.service.dto.vehicule.VeloRequestDTO;
import com.accenture.service.dto.vehicule.VeloResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VeloMapper {
    Velo toVelo(VeloRequestDTO clientRequestDTO);
    VeloResponseDTO toVeloResponseDTO(Velo velo);
}
