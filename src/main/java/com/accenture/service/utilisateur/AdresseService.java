package com.accenture.service.utilisateur;

import com.accenture.exception.UtilisateurException;
import com.accenture.service.dto.utilisateur.AdresseRequestDTO;
import com.accenture.service.dto.utilisateur.AdresseResponseDTO;

import java.util.List;

public interface AdresseService {
    AdresseResponseDTO find(int id) throws UtilisateurException;
    AdresseResponseDTO save(AdresseRequestDTO adresseRequestDTO) throws UtilisateurException;
    List<AdresseRequestDTO> findAll();
    void delete(int id) throws UtilisateurException;
}
