package com.accenture.service.vehicule;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.vehicule.VeloRequestDTO;
import com.accenture.service.dto.vehicule.VeloResponseDTO;

import java.util.List;

public interface VeloService {
    VeloResponseDTO find(int id) throws VehiculeException;
    VeloResponseDTO save(VeloRequestDTO clientRequestDTO) throws VehiculeException;
    List<VeloResponseDTO> findAll();
    void delete(int id) throws VehiculeException;
}
