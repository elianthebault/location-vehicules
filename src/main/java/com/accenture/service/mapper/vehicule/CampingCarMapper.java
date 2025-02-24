package com.accenture.service.mapper.vehicule;

import com.accenture.repository.entity.vehicule.CampingCar;
import com.accenture.service.dto.vehicule.CampingCarRequestDTO;
import com.accenture.service.dto.vehicule.CampingCarResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CampingCarMapper {
    CampingCar toCampingCar(CampingCarRequestDTO clientRequestDTO);
    CampingCarResponseDTO toCampingCarResponseDTO(CampingCar campingCar);
}
