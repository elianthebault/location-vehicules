package com.accenture.service.vehicule;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.vehicule.VeloRequestDTO;
import com.accenture.service.dto.vehicule.VeloResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface VeloService {
    VeloResponseDTO find(int id) throws VehiculeException;
    VeloResponseDTO save(VeloRequestDTO veloRequestDTO) throws VehiculeException;
    List<VeloResponseDTO> findAll();
    void delete(int id) throws VehiculeException;
    VeloResponseDTO updateFields(int id, VeloRequestDTO veloRequestDTO) throws VehiculeException;
    List<VeloResponseDTO> findVehiculesNotRentedBetween(LocalDate dateDebut, LocalDate dateFin);
}
