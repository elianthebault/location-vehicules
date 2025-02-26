package com.accenture.service.location;

import com.accenture.exception.LocationException;
import com.accenture.service.dto.location.LocationRequestDTO;
import com.accenture.service.dto.location.LocationResponseDTO;
import com.accenture.shared.enumeration.CategorieVehicule;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.util.List;

public interface LocationService {
    LocationResponseDTO find(int id) throws LocationException;
    LocationResponseDTO save(LocationRequestDTO locationRequestDTO) throws LocationException, EntityNotFoundException;
    List<LocationResponseDTO> findAll();
    void delete(int id) throws EntityNotFoundException;
    LocationResponseDTO updateFields(int id, LocationRequestDTO locationRequestDTO) throws EntityNotFoundException;
}
