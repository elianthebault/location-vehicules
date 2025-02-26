package com.accenture.service.mapper.location;

import com.accenture.repository.entity.location.Accessoire;
import com.accenture.service.dto.location.AccessoireRequestDTO;
import com.accenture.service.dto.location.AccessoireResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccessoireMapper {
    Accessoire toAccessoire(AccessoireRequestDTO accessoireRequestDTO);
    AccessoireResponseDTO toAccessoireResponseDTO(Accessoire accessoire);
}
