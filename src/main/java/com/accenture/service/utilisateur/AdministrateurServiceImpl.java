package com.accenture.service.utilisateur;

import com.accenture.exception.UtilisateurException;
import com.accenture.repository.dao.utilisateur.AdministrateurDAO;
import com.accenture.repository.entity.utilisateur.Administrateur;
import com.accenture.service.dto.utilisateur.AdministrateurRequestDTO;
import com.accenture.service.dto.utilisateur.AdministrateurResponseDTO;
import com.accenture.service.mapper.utilisateur.AdministrateurMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service Administrateur
 */

@Service
public class AdministrateurServiceImpl implements AdministrateurService, StringValidation {
    private final AdministrateurDAO administrateurDAO;
    private final AdministrateurMapper administrateurMapper;
    private final PasswordEncoder passwordEncoder;

    public AdministrateurServiceImpl(AdministrateurDAO administrateurDAO, AdministrateurMapper administrateurMapper, PasswordEncoder passwordEncoder) {
        this.administrateurDAO = administrateurDAO;
        this.administrateurMapper = administrateurMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Permet de retrouver un Administrateur dans la base de donnée.
     *
     * @param id
     * @return Un objet AdministrateurResponseDTO.
     * @throws EntityNotFoundException Si l'ID de l'Administrateur n'est pas trouvé dans la base de donnée.
     */
    @Override
    public AdministrateurResponseDTO find(int id) throws UtilisateurException {
        Optional<Administrateur> optAdministrateur = administrateurDAO.findById(id);
        if (optAdministrateur.isEmpty())
            throw new EntityNotFoundException("ID est invalide");
        return administrateurMapper.toAdministrateurResponseDTO(optAdministrateur.get());
    }

    /**
     * Permet d'enregistrer un Administrateur dans la base de donnée.
     *
     * @param administrateurRequestDTO
     * @return Un objet AdministrateurResponseDTO.
     * @throws UtilisateurException Depuis la méthode checkAdministrateur() si des informations sont manquantes.
     * @throws IllegalArgumentException Si l'email de l'Administrateur est déjà enregistré dans la base de donnée.
     */
    @Override
    public AdministrateurResponseDTO save(AdministrateurRequestDTO administrateurRequestDTO) throws UtilisateurException, IllegalArgumentException {
        checkAdministrateur(administrateurRequestDTO);
        if (administrateurDAO.existsByEmail(administrateurRequestDTO.email())) {
            throw new IllegalArgumentException("Cet email est déjà utilisé");
        }
        Administrateur administrateur = administrateurMapper.toAdministrateur(administrateurRequestDTO);
        administrateur.setPassword(passwordEncoder.encode(administrateur.getPassword()));
        administrateur.setRole("ROLE_ADMIN");
        Administrateur returnedAdministrateur = administrateurDAO.save(administrateur);
        return administrateurMapper.toAdministrateurResponseDTO(returnedAdministrateur);
    }

    /**
     * Permet de retrouver tous les Administrateurs enregistrés dans la base de donnée.
     *
     * @return Une liste de AdministrateurResponseDTO.
     */
    @Override
    public List<AdministrateurResponseDTO> findAll() {
        return administrateurDAO.findAll().stream()
                .map(administrateur -> administrateurMapper.toAdministrateurResponseDTO(administrateur))
                .toList();
    }

    /**
     * Supprime un Administrateur dans la base de donnée.
     *
     * @param id
     * @throws UtilisateurException Si l'ID de l'Administrateur n'est pas trouvé dans la base de donnée.
     */
    @Override
    public void delete(int id) throws UtilisateurException {
        if (administrateurDAO.existsById(id))
            administrateurDAO.deleteById(id);
        else
            throw new UtilisateurException("ID est invalide");
    }

    /**
     * Permet à un Administrateur de se connecter.
     *
     * @param email
     * @param password
     * @return Un objet AdministrateurResponseDTO
     * @throws UtilisateurException Depuis la méthode checkLogin() si le mot de passe est erroné.
     * @throws EntityNotFoundException Depuis la méthode checkLogin() si l'email n'est pas trouvé dans la base de donnée.
     */
    @Override
    public AdministrateurResponseDTO loginAdministrateur(String email, String password) throws UtilisateurException, EntityNotFoundException {
        Optional<Administrateur> optAdministrateur = checkLogin(email, password);
        return administrateurMapper.toAdministrateurResponseDTO(optAdministrateur.get());
    }

    /**
     * Met à jour toutes ou certaines informations de l'Administrateur.
     *
     * @param email
     * @param password
     * @param administrateurRequestDTO
     * @return Un Objet AdministrateurResponseDTO
     * @throws UtilisateurException Depuis la méthode checkLogin() ou checkAdministrateur() si des informations sont erronées.
     * @throws EntityNotFoundException Depuis la méthode checkLogin() si l'email n'est pas trouvé dans la base de donnée.
     */
    @Override
    public AdministrateurResponseDTO updateFields(String email, String password, AdministrateurRequestDTO administrateurRequestDTO) throws UtilisateurException, EntityNotFoundException {
        Optional<Administrateur> optAdministrateur = checkLogin(email, password);
        checkExistingAdministrateur(administrateurMapper.toAdministrateur(administrateurRequestDTO), optAdministrateur.get());
        checkAdministrateur(administrateurRequestDTO);
        return administrateurMapper.toAdministrateurResponseDTO(administrateurDAO.save(optAdministrateur.get()));
    }

    /*
     * PRIVATE METHODS
     */

    private static void checkAdministrateur(AdministrateurRequestDTO administrateurRequestDTO) {
        //TODO put it into StringValidation interface
        if (administrateurRequestDTO == null)
            throw new UtilisateurException("Administrateur est null");
        if (administrateurRequestDTO.nom() == null
                || administrateurRequestDTO.nom().isBlank())
            throw new UtilisateurException("Nom est null ou vide");
        if (administrateurRequestDTO.prenom() == null
                || administrateurRequestDTO.prenom().isBlank())
            throw new UtilisateurException("Prénom est null ou vide");
        if (administrateurRequestDTO.email() == null
                || administrateurRequestDTO.email().isBlank())
            throw new UtilisateurException("Email est null ou vide");
        if (!StringValidation.checkPassword(administrateurRequestDTO.password()))
            throw new UtilisateurException("Mot de passe invalide");
        if (administrateurRequestDTO.fonction() == null
                || administrateurRequestDTO.fonction().isBlank())
            throw new UtilisateurException("Fonction est null ou vide");
    }

    /**
     * Compare l'Administrateur envoyé depuis la méthode updateFields() et l'Administrateur existant en base de données.
     *
     * @param administrateur
     * @param existingAdministrateur
     */
    private static void checkExistingAdministrateur(Administrateur administrateur, Administrateur existingAdministrateur) {
        if (administrateur.getNom() != null)
            existingAdministrateur.setNom(administrateur.getNom());
        if (administrateur.getPrenom() != null)
            existingAdministrateur.setPrenom(administrateur.getPrenom());
        if (administrateur.getEmail() != null)
            existingAdministrateur.setEmail(administrateur.getEmail());
        if (administrateur.getPassword() != null)
            existingAdministrateur.setPassword(administrateur.getPassword());
        if (administrateur.getFonction() != null)
            existingAdministrateur.setFonction(administrateur.getFonction());
    }

    /**
     * Vérifie aue le Administrateur existe bien en base de données.
     *
     * @param email
     * @param password
     * @return Un Optional<Administrateur>.
     */
    private Optional<Administrateur> checkLogin(String email, String password) {
        Optional<Administrateur> optAdministrateur = administrateurDAO.findByEmail(email);
        if (optAdministrateur.isEmpty())
            throw new EntityNotFoundException("Email non trouvé");
        if (!passwordEncoder.matches(password, optAdministrateur.get().getPassword()))
            throw new UtilisateurException("Mot de passe invalide");
        return optAdministrateur;
    }
}
