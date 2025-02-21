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

/**
 * Service Client
 */

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

    /**
     * Permet de retrouver un Client dans la base de donnée.
     *
     * @param id
     * @return Un objet ClientResponseDTO.
     * @throws EntityNotFoundException Si l'ID du client n'est pas trouvé dans la base de donnée.
     */
    @Override
    public ClientResponseDTO find(int id) throws EntityNotFoundException {
        Optional<Client> optClient = clientDAO.findById(id);
        if (optClient.isEmpty())
            throw new EntityNotFoundException("ID est invalide");
        return clientMapper.toClientResponseDTO(optClient.get());
    }

    /**
     * Permet d'enregistrer un Client dans la base de donnée.
     *
     * @param clientRequestDTO
     * @return Un objet ClientResponseDTO.
     * @throws UtilisateurException Depuis la méthode checkClient() si des informations sont manquantes.
     * @throws IllegalArgumentException Si l'email du Client est déjà enregistré dans la base de donnée.
     */
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

    /**
     * Permet de retrouver tous les Clients enregistrés dans la base de donnée.
     *
     * @return Une liste de ClientResponseDTO.
     */
    @Override
    public List<ClientResponseDTO> findAll() {
        return clientDAO.findAll().stream()
                .map(client -> clientMapper.toClientResponseDTO(client))
                .toList();
    }

    /**
     * Supprime un Client dans la base de donnée.
     *
     * @param id
     * @throws UtilisateurException Si l'ID du client n'est pas trouvé dans la base de donnée.
     */
    @Override
    public void delete(int id) throws UtilisateurException {
        if (clientDAO.existsById(id))
            clientDAO.deleteById(id);
        else
            throw new UtilisateurException("ID est invalide");
    }

    /**
     * Permet à un Client de se connecter.
     *
     * @param email
     * @param password
     * @return Un objet ClientResponseDTO
     * @throws UtilisateurException Depuis la méthode checkLogin() si le mot de passe est erroné.
     * @throws EntityNotFoundException Depuis la méthode checkLogin() si l'email n'est pas trouvé dans la base de donnée.
     */
    @Override
    public ClientResponseDTO loginClient(String email, String password) throws UtilisateurException, EntityNotFoundException {
        Optional<Client> optClient = checkLogin(email, password);
        return clientMapper.toClientResponseDTO(optClient.get());
    }

    /**
     * Met à jour toutes ou certaines informations du Client.
     *
     * @param email
     * @param password
     * @param clientRequestDTO
     * @return Un Objet ClientResponseDTO
     * @throws UtilisateurException Depuis la méthode checkLogin() ou checkClient() si des informations sont erronées.
     * @throws EntityNotFoundException Depuis la méthode checkLogin() si l'email n'est pas trouvé dans la base de donnée.
     */
    @Override
    public ClientResponseDTO updateFields(String email, String password, ClientRequestDTO clientRequestDTO) throws UtilisateurException, EntityNotFoundException {
        Optional<Client> optClient = checkLogin(email, password);
        checkExistingClient(clientMapper.toClient(clientRequestDTO), optClient.get());
        checkClient(clientRequestDTO);
        return clientMapper.toClientResponseDTO(clientDAO.save(optClient.get()));
    }

    /*
     * PRIVATE METHODS
     */

    /**
     * Vérifie aue le Client existe bien en base de données.
     *
     * @param email
     * @param password
     * @return Un Optional<Client>.
     */
    public Optional<Client> checkLogin(String email, String password) {
        Optional<Client> optClient = clientDAO.findByEmail(email);
        if (optClient.isEmpty())
            throw new EntityNotFoundException("Email non trouvé");
        if (!passwordEncoder.matches(password, optClient.get().getPassword()))
            throw new UtilisateurException("Mot de passe invalide");
        return optClient;
    }

    /**
     * Vérifie si les informations du Client sont dûment remplies.
     *
     * @param clientRequestDTO
     * @throws UtilisateurException Dès qu'une information est erronée.
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

    /**
     * Compare le Client envoyé depuis la méthode updateFields() et le Client existant en base de données.
     *
     * @param client
     * @param existingClient
     */
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
}
