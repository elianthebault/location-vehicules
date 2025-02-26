package com.accenture.service.vehicule;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.vehicule.UtilitaireRequestDTO;
import com.accenture.service.dto.vehicule.UtilitaireResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface UtilitaireService {

    UtilitaireResponseDTO find(int id) throws VehiculeException;
    UtilitaireResponseDTO save(UtilitaireRequestDTO utilitaireRequestDTO) throws VehiculeException;
    List<UtilitaireResponseDTO> findAll();
    void delete(int id) throws VehiculeException;
    UtilitaireResponseDTO updateFields(int id, UtilitaireRequestDTO utilitaireRequestDTO) throws VehiculeException;
    List<UtilitaireResponseDTO> findVehiculesNotRentedBetween(LocalDate dateDebut, LocalDate dateFin);
}
