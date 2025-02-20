package com.accenture.service.utilisateur;

import com.accenture.exception.UtilisateurException;
import com.accenture.service.dto.utilisateur.ClientRequestDTO;
import com.accenture.service.dto.utilisateur.ClientResponseDTO;

import java.util.List;

public interface ClientService {
    ClientResponseDTO find(int id) throws UtilisateurException;
    ClientResponseDTO save(ClientRequestDTO clientRequestDTO) throws UtilisateurException;
    List<ClientResponseDTO> findAll();
    void delete(int id) throws UtilisateurException;
}
