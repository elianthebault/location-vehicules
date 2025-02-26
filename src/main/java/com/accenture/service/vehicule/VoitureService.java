package com.accenture.service.vehicule;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.vehicule.ListeVehiculesDTO;
import com.accenture.service.dto.vehicule.VoitureRequestDTO;
import com.accenture.service.dto.vehicule.VoitureResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface VoitureService {
    VoitureResponseDTO find(int id) throws VehiculeException;
    VoitureResponseDTO save(VoitureRequestDTO voitureRequestDTO) throws VehiculeException;
    List<VoitureResponseDTO> findAll();
    void delete(int id) throws VehiculeException;
    VoitureResponseDTO updateFields(int id, VoitureRequestDTO voitureRequestDTO) throws VehiculeException;
    List<VoitureResponseDTO> findVehiculesNotRentedBetween(LocalDate dateDebut, LocalDate dateFin);
}
