package com.accenture.service.utilisateur;

import com.accenture.exception.UtilisateurException;
import com.accenture.service.dto.utilisateur.AdministrateurRequestDTO;
import com.accenture.service.dto.utilisateur.AdministrateurResponseDTO;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface AdministrateurService {
    AdministrateurResponseDTO find(int id) throws UtilisateurException;
    AdministrateurResponseDTO save(AdministrateurRequestDTO administrateurRequestDTO) throws UtilisateurException;
    List<AdministrateurResponseDTO> findAll();
    void delete(int id) throws UtilisateurException;
    AdministrateurResponseDTO loginAdministrateur(String email, String password) throws UtilisateurException, EntityNotFoundException;
    AdministrateurResponseDTO updateFields(String email, String password, AdministrateurRequestDTO administrateurRequestDTO) throws UtilisateurException;
}
