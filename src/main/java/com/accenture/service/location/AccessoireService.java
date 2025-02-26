package com.accenture.service.location;

import com.accenture.exception.LocationException;
import com.accenture.service.dto.location.AccessoireRequestDTO;
import com.accenture.service.dto.location.AccessoireResponseDTO;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface AccessoireService {
    AccessoireResponseDTO find(int id) throws LocationException;
    AccessoireResponseDTO save(AccessoireRequestDTO accessoireRequestDTO) throws LocationException;
    List<AccessoireResponseDTO> findAll();
    void delete(int id) throws LocationException;
    AccessoireResponseDTO updateFields(AccessoireRequestDTO accessoireRequestDTO) throws LocationException;
}
