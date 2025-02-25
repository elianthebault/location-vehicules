package com.accenture.service.vehicule;

import com.accenture.exception.VehiculeException;
import com.accenture.repository.dao.vehicule.VeloDAO;
import com.accenture.repository.entity.vehicule.Velo;
import com.accenture.service.dto.vehicule.VeloRequestDTO;
import com.accenture.service.dto.vehicule.VeloResponseDTO;
import com.accenture.service.mapper.vehicule.VeloMapper;
import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.TypeVelo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VeloServiceImpl implements VeloService {
    private final VeloDAO veloDAO;
    private final VeloMapper veloMapper;

    public VeloServiceImpl(VeloDAO veloDAO, VeloMapper veloMapper) {
        this.veloDAO = veloDAO;
        this.veloMapper = veloMapper;
    }

    @Override
    public VeloResponseDTO find(int id) throws VehiculeException {
        Optional<Velo> optVelo = veloDAO.findById(id);
        if (optVelo.isEmpty())
            throw new EntityNotFoundException("ID est invalide");
        return veloMapper.toVeloResponseDTO(optVelo.get());
    }

    @Override
    public VeloResponseDTO save(VeloRequestDTO veloRequestDTO) throws VehiculeException {
        checkVelo(veloRequestDTO);
        Velo velo = veloMapper.toVelo(veloRequestDTO);
        velo.setListePermis(null);
        if (velo.getElectrique() == true)
            if (velo.getAutonomieBatterie() == null
                    || velo.getAutonomieBatterie() <= 0)
                throw new VehiculeException("Si vélo électrique, l'autonomie de la batterie doit être renseignée.");
        Velo returnedVelo = veloDAO.save(velo);
        return veloMapper.toVeloResponseDTO(returnedVelo);
    }

    @Override
    public List<VeloResponseDTO> findAll() {
        return veloDAO.findAll().stream()
                .map(velo -> veloMapper.toVeloResponseDTO(velo))
                .toList();
    }

    @Override
    public void delete(int id) throws VehiculeException {
        if (veloDAO.existsById(id))
            veloDAO.deleteById(id);
        else
            throw new VehiculeException("ID non trouvée");
    }

    @Override
    public VeloResponseDTO updateFields(int id, VeloRequestDTO veloRequestDTO) throws VehiculeException {
        Optional<Velo> optVelo = veloDAO.findById(id);
        if (optVelo.isEmpty())
            throw new VehiculeException("ID non trouvée");
        checkExistingVelo(veloMapper.toVelo(veloRequestDTO), optVelo.get());
        return veloMapper.toVeloResponseDTO(veloDAO.save(optVelo.get()));
    }

    /*
     * PRIVATE METHODS
     */

    private static void checkExistingVelo(Velo velo, Velo existingVelo) {
        if (velo.getMarque() != null)
            existingVelo.setMarque(velo.getMarque());
        if (velo.getModele() != null)
            existingVelo.setModele(velo.getModele());
        if (velo.getCouleur() != null)
            existingVelo.setCouleur(velo.getCouleur());
        if (velo.getTarif() != null)
            existingVelo.setTarif(velo.getTarif());
        if (velo.getKilometrage() != null)
            existingVelo.setKilometrage(velo.getKilometrage());
        if (velo.getActif() != null)
            existingVelo.setActif(velo.getActif());
        if (velo.getRetire() != null)
            existingVelo.setRetire(velo.getRetire());
        if (velo.getPoids() != null)
            existingVelo.setPoids(velo.getPoids());
        if (velo.getTailleCadre() != null)
            existingVelo.setTailleCadre(velo.getTailleCadre());
        if (velo.getElectrique() != null)
            existingVelo.setElectrique(velo.getElectrique());
        if (velo.getAutonomieBatterie() != null)
            existingVelo.setAutonomieBatterie(velo.getAutonomieBatterie());
        if (velo.getFreinsADisque() != null)
            existingVelo.setFreinsADisque(velo.getFreinsADisque());
        if (velo.getTypeVelo() != null)
            existingVelo.setTypeVelo(velo.getTypeVelo());
    }

    private static void checkVelo(VeloRequestDTO veloRequestDTO) {
        if (veloRequestDTO == null)
            throw new VehiculeException("La velo est null");
        if (veloRequestDTO.marque() == null
                || veloRequestDTO.marque().isBlank())
            throw new VehiculeException("La marque est null ou vide");
        if (veloRequestDTO.modele() == null
                || veloRequestDTO.modele().isBlank())
            throw new VehiculeException("Le modele est null ou vide");
        if (veloRequestDTO.couleur() == null
                || veloRequestDTO.couleur().isBlank())
            throw new VehiculeException("La couleur est null ou vide");
        if (veloRequestDTO.tarif() < 0)
            throw new VehiculeException("Le tarif est null ou vide");
        if (veloRequestDTO.kilometrage() < 0)
            throw new VehiculeException("Le kilométrage est null ou vide");
        if (veloRequestDTO.actif() == null)
            throw new VehiculeException("Le status de location est null");
        if (veloRequestDTO.retire() == null)
            throw new VehiculeException("Le status de validité est null");
        if (veloRequestDTO.poids() == null)
            throw new VehiculeException("Le poids est null");
        if (veloRequestDTO.tailleCadre() == null)
            throw new VehiculeException("La taille du cadre est null");
        if (veloRequestDTO.electrique() == null)
            throw new VehiculeException("La motricité est null");
        if (veloRequestDTO.electrique() == true && veloRequestDTO.autonomieBatterie() == null)
            throw new VehiculeException("L'autonomie de la batterie est null");
        if (veloRequestDTO.freinsADisque() == null)
            throw new VehiculeException("Le freinage est null");
        if (veloRequestDTO.typeVelo() == null)
            throw new VehiculeException("Le type de velo est null");
    }
}
