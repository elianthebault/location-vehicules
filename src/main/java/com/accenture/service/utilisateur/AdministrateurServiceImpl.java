package com.accenture.service.utilisateur;

import com.accenture.exception.UtilisateurException;
import com.accenture.repository.dao.utilisateur.AdministrateurDAO;
import com.accenture.repository.entity.utilisateur.Administrateur;
import com.accenture.service.dto.utilisateur.AdministrateurRequestDTO;
import com.accenture.service.dto.utilisateur.AdministrateurResponseDTO;
import com.accenture.service.mapper.AdministrateurMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrateurServiceImpl implements AdministrateurService, StringValidation {
    private final AdministrateurDAO administrateurDAO;
    private final AdministrateurMapper administrateurMapper;

    public AdministrateurServiceImpl(AdministrateurDAO administrateurDAO, AdministrateurMapper administrateurMapper) {
        this.administrateurDAO = administrateurDAO;
        this.administrateurMapper = administrateurMapper;
    }

    @Override
    public AdministrateurResponseDTO find(int id) throws UtilisateurException {
        Optional<Administrateur> optAdministrateur = administrateurDAO.findById(id);
        if (optAdministrateur.isEmpty())
            throw new EntityNotFoundException("ID est invalide");
        return administrateurMapper.toAdministrateurResponseDTO(optAdministrateur.get());
    }

    @Override
    public AdministrateurResponseDTO save(AdministrateurRequestDTO administrateurRequestDTO) throws UtilisateurException {
        checkAdministrateur(administrateurRequestDTO);
        if (!StringValidation.checkPassword(administrateurRequestDTO.password()))
            throw new UtilisateurException("Mot de passe invalide");
        Administrateur administrateur = administrateurMapper.toAdministrateur(administrateurRequestDTO);
        Administrateur returnedAdministrateur = administrateurDAO.save(administrateur);
        return administrateurMapper.toAdministrateurResponseDTO(returnedAdministrateur);
    }

    @Override
    public List<AdministrateurResponseDTO> findAll() {
        return administrateurDAO.findAll().stream()
                .map(administrateur -> administrateurMapper.toAdministrateurResponseDTO(administrateur))
                .toList();
    }

    @Override
    public void delete(int id) throws UtilisateurException {
        if (administrateurDAO.existsById(id))
            administrateurDAO.deleteById(id);
        else
            throw new UtilisateurException("ID est invalide");
    }

    /*
     * PRIVATE METHODS
     */

    private static void checkAdministrateur(AdministrateurRequestDTO administrateurRequestDTO) {
        //TODO put it into StringValidation interface
        if (administrateurRequestDTO == null)
            throw new UtilisateurException("Client est null");
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
}
