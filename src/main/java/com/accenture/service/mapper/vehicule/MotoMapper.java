package com.accenture.service.mapper.vehicule;

import com.accenture.repository.entity.vehicule.Moto;
import com.accenture.service.dto.vehicule.MotoRequestDTO;
import com.accenture.service.dto.vehicule.MotoResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MotoMapper {
    Moto toMoto(MotoRequestDTO clientRequestDTO);
    MotoResponseDTO toMotoResponseDTO(Moto moto);
}
