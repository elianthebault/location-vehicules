package com.accenture.service.vehicule;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.vehicule.CampingCarRequestDTO;
import com.accenture.service.dto.vehicule.CampingCarResponseDTO;

import java.util.List;

public interface CampingCarService {
    CampingCarResponseDTO find(int id) throws VehiculeException;
    CampingCarResponseDTO save(CampingCarRequestDTO campingCarRequestDTO) throws VehiculeException;
    List<CampingCarResponseDTO> findAll();
    void delete(int id) throws VehiculeException;
    CampingCarResponseDTO updateFields(int id, CampingCarRequestDTO campingCarRequestDTO) throws VehiculeException;
}
