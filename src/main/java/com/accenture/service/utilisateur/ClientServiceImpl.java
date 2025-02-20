package com.accenture.service.utilisateur;

import com.accenture.exception.UtilisateurException;
import com.accenture.repository.dao.utilisateur.ClientDAO;
import com.accenture.repository.entity.utilisateur.Client;
import com.accenture.service.dto.utilisateur.ClientRequestDTO;
import com.accenture.service.dto.utilisateur.ClientResponseDTO;
import com.accenture.service.mapper.ClientMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService, StringValidation {
    private final ClientDAO clientDAO;
    private final ClientMapper clientMapper;

    public ClientServiceImpl(ClientDAO clientDAO, ClientMapper clientMapper) {
        this.clientDAO = clientDAO;
        this.clientMapper = clientMapper;
    }

    @Override
    public ClientResponseDTO find(int id) throws UtilisateurException, EntityNotFoundException {
        Optional<Client> optClient = clientDAO.findById(id);
        if (optClient.isEmpty())
            throw new EntityNotFoundException("ID est invalide");
        return clientMapper.toClientResponseDTO(optClient.get());
    }

    @Override
    public ClientResponseDTO save(ClientRequestDTO clientRequestDTO) throws UtilisateurException {
        checkClient(clientRequestDTO);
        Client client = clientMapper.toClient(clientRequestDTO);
        Client returnedClient = clientDAO.save(client);
        return clientMapper.toClientResponseDTO(returnedClient);
    }

    @Override
    public List<ClientResponseDTO> findAll() {
        return clientDAO.findAll().stream()
                .map(client -> clientMapper.toClientResponseDTO(client))
                .toList();
    }

    @Override
    public void delete(int id) throws UtilisateurException {
        if (clientDAO.existsById(id))
            clientDAO.deleteById(id);
        else
            throw new UtilisateurException("ID est invalide");
    }

    /*
     * PRIVATE METHODS
     */

    private static void checkClient(ClientRequestDTO clientRequestDTO) throws UtilisateurException {
        //TODO put it into StringValidation interface
        if (clientRequestDTO == null)
            throw new UtilisateurException("Client est null");
        if (clientRequestDTO.nom() == null
                || clientRequestDTO.nom().isBlank())
            throw new UtilisateurException("Nom est null ou vide");
        if (clientRequestDTO.prenom() == null
                || clientRequestDTO.prenom().isBlank())
            throw new UtilisateurException("Prénom est null ou vide");
        if (clientRequestDTO.email() == null
                || clientRequestDTO.email().isBlank())
            throw new UtilisateurException("Email est null ou vide");
        if (!StringValidation.checkPassword(clientRequestDTO.password()))
            throw new UtilisateurException("Mot de passe invalide");
        if (clientRequestDTO.adresse() == null)
            throw new UtilisateurException("Adresse est null");
        if (clientRequestDTO.adresse().adresse() == null
                || clientRequestDTO.adresse().adresse().isBlank())
            throw new UtilisateurException("La rue de l'adresse est null ou vide");
        if (clientRequestDTO.adresse().codePostal() == null
                || clientRequestDTO.adresse().codePostal().isBlank())
            throw new UtilisateurException("Le code postal de l'adresse est null ou vide");
        if (clientRequestDTO.adresse().ville() == null
                || clientRequestDTO.adresse().ville().isBlank())
            throw new UtilisateurException("La ville de l'adresse est null ou vide");
        if (clientRequestDTO.dateNaissance() == null
                || Period.between(clientRequestDTO.dateNaissance(), LocalDate.now()).getYears() < 18)
            throw new UtilisateurException("La date de naissance est null ou le client est mineur");
        if (clientRequestDTO.listePermis() == null)
            throw new UtilisateurException("La liste des permis est null");
    }
}
