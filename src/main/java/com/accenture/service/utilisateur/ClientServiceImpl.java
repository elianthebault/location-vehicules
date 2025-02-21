package com.accenture.service.utilisateur;

import com.accenture.exception.UtilisateurException;
import com.accenture.repository.dao.utilisateur.ClientDAO;
import com.accenture.repository.entity.utilisateur.Client;
import com.accenture.service.dto.utilisateur.ClientRequestDTO;
import com.accenture.service.dto.utilisateur.ClientResponseDTO;
import com.accenture.service.mapper.ClientMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService, StringValidation {
    private final ClientDAO clientDAO;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder;

    public ClientServiceImpl(ClientDAO clientDAO, ClientMapper clientMapper, PasswordEncoder passwordEncoder) {
        this.clientDAO = clientDAO;
        this.clientMapper = clientMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ClientResponseDTO find(int id) throws UtilisateurException, EntityNotFoundException {
        Optional<Client> optClient = clientDAO.findById(id);
        if (optClient.isEmpty())
            throw new EntityNotFoundException("ID est invalide");
        return clientMapper.toClientResponseDTO(optClient.get());
    }

    @Override
    public ClientResponseDTO save(ClientRequestDTO clientRequestDTO) throws UtilisateurException, IllegalArgumentException {
        checkClient(clientRequestDTO);
        if (clientDAO.existsByEmail(clientRequestDTO.email())) {
            throw new IllegalArgumentException("Cet email est déjà utilisé");
        }
        Client client = clientMapper.toClient(clientRequestDTO);
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        client.setRole("ROLE_CLIENT");
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

    @Override
    public ClientResponseDTO loginClient(String email, String password) throws UtilisateurException, EntityNotFoundException {
        Optional<Client> optClient = checkLogin(email, password);
        return clientMapper.toClientResponseDTO(optClient.get());
    }

    @Override
    public ClientResponseDTO updateFields(String email, String password, ClientRequestDTO clientRequestDTO) throws EntityNotFoundException {
        Optional<Client> optClient = checkLogin(email, password);
        checkExistingClient(clientMapper.toClient(clientRequestDTO), optClient.get());
        checkClient(clientRequestDTO);
        return clientMapper.toClientResponseDTO(clientDAO.save(optClient.get()));
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

    private static void checkExistingClient(Client client, Client existingClient) {
        if (client.getNom() != null)
            existingClient.setNom(client.getNom());
        if (client.getPrenom() != null)
            existingClient.setPrenom(client.getPrenom());
        if (client.getEmail() != null)
            existingClient.setEmail(client.getEmail());
        if (client.getPassword() != null)
            existingClient.setPassword(client.getPassword());
        if (client.getAdresse() != null) {
            if (client.getAdresse().getAdresse() != null
                    && !client.getAdresse().getAdresse().isBlank())
                existingClient.getAdresse().setAdresse(client.getAdresse().getAdresse());
            if (client.getAdresse().getCodePostal() != null
                    && !client.getAdresse().getCodePostal().isBlank())
                existingClient.getAdresse().setCodePostal(client.getAdresse().getCodePostal());
            if (client.getAdresse().getVille() != null
                    && !client.getAdresse().getVille().isBlank())
                existingClient.getAdresse().setVille(client.getAdresse().getVille());
        }
        if (client.getDateNaissance() != null)
            existingClient.setDateNaissance(client.getDateNaissance());
        if (client.getListePermis() != null)
            existingClient.setListePermis(client.getListePermis());
    }

    private Optional<Client> checkLogin(String email, String password) {
        Optional<Client> optClient = clientDAO.findByEmail(email);
        if (optClient.isEmpty())
            throw new EntityNotFoundException("Email non trouvé");
        if (!passwordEncoder.matches(password, optClient.get().getPassword()))
            throw new UtilisateurException("Mot de passe invalide");
        return optClient;
    }
}
