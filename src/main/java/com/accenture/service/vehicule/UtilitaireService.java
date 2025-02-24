package com.accenture.service.vehicule;

import com.accenture.exception.VehiculeException;
import com.accenture.service.dto.vehicule.UtilitaireRequestDTO;
import com.accenture.service.dto.vehicule.UtilitaireResponseDTO;

import java.util.List;

public interface UtilitaireService {
    UtilitaireResponseDTO find(int id) throws VehiculeException;
    UtilitaireResponseDTO save(UtilitaireRequestDTO clientRequestDTO) throws VehiculeException;
    List<UtilitaireResponseDTO> findAll();
    void delete(int id) throws VehiculeException;
}
