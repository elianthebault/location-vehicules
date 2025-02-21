package com.accenture.service.utilisateur;

import com.accenture.exception.UtilisateurException;
import com.accenture.service.dto.utilisateur.ClientRequestDTO;
import com.accenture.service.dto.utilisateur.ClientResponseDTO;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface ClientService {
    ClientResponseDTO find(int id) throws UtilisateurException;
    ClientResponseDTO save(ClientRequestDTO clientRequestDTO) throws UtilisateurException;
    List<ClientResponseDTO> findAll();
    void delete(int id) throws UtilisateurException;
    ClientResponseDTO loginClient(String email, String password) throws UtilisateurException, EntityNotFoundException;
    ClientResponseDTO updateFields(String email, String password, ClientRequestDTO clientRequestDTO) throws UtilisateurException;
}
