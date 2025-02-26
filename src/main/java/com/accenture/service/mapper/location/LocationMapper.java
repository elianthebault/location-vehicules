package com.accenture.service.mapper.location;

import com.accenture.repository.entity.location.Location;
import com.accenture.service.dto.location.LocationRequestDTO;
import com.accenture.service.dto.location.LocationResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LocationMapper {
    Location toLocation(LocationRequestDTO locationRequestDTO);
    LocationResponseDTO toLocationResponseDTO(Location location);
}
