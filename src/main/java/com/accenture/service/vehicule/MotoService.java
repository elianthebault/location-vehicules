package com.accenture.service.vehicule;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.vehicule.MotoRequestDTO;
import com.accenture.service.dto.vehicule.MotoResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface MotoService {
    MotoResponseDTO find(int id) throws VehiculeException;
    MotoResponseDTO save(MotoRequestDTO motoRequestDTO) throws VehiculeException;
    List<MotoResponseDTO> findAll();
    void delete(int id) throws VehiculeException;
    MotoResponseDTO updateFields(int id, MotoRequestDTO motoRequestDTO) throws VehiculeException;
    List<MotoResponseDTO> findVehiculesNotRentedBetween(LocalDate dateDebut, LocalDate dateFin);
}
