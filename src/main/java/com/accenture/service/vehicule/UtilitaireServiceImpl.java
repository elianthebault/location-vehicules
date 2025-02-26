package com.accenture.service.vehicule;

import com.accenture.exception.VehiculeException;
import com.accenture.repository.dao.vehicule.UtilitaireDAO;
import com.accenture.repository.entity.vehicule.Utilitaire;
import com.accenture.service.dto.vehicule.UtilitaireRequestDTO;
import com.accenture.service.dto.vehicule.UtilitaireResponseDTO;
import com.accenture.service.mapper.vehicule.UtilitaireMapper;
import com.accenture.shared.enumeration.Permis;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UtilitaireServiceImpl implements UtilitaireService {
    private final UtilitaireDAO utilitaireDAO;
    private final UtilitaireMapper utilitaireMapper;

    public UtilitaireServiceImpl(UtilitaireDAO utilitaireDAO, UtilitaireMapper utilitaireMapper) {
        this.utilitaireDAO = utilitaireDAO;
        this.utilitaireMapper = utilitaireMapper;
    }

    @Override
    public UtilitaireResponseDTO find(int id) throws VehiculeException {
        Optional<Utilitaire> optUtilitaire = utilitaireDAO.findById(id);
        if (optUtilitaire.isEmpty())
            throw new EntityNotFoundException("ID est invalide");
        return utilitaireMapper.toUtilitaireResponseDTO(optUtilitaire.get());
    }

    @Override
    public UtilitaireResponseDTO save(UtilitaireRequestDTO utilitaireRequestDTO) throws VehiculeException {
        checkUtilitaire(utilitaireRequestDTO);
        Utilitaire utilitaire = utilitaireMapper.toUtilitaire(utilitaireRequestDTO);
        List<Permis> listePermis = new ArrayList<>();
        if (utilitaire.getPoidsPTAC() < 3.5)
            listePermis.add(Permis.B);
        else if (utilitaire.getPoidsPTAC() >= 3.5 && utilitaire.getPoidsPTAC() <= 7.5)
            listePermis.add(Permis.C1);
        utilitaire.setListePermis(listePermis);
        Utilitaire returnedUtilitaire = utilitaireDAO.save(utilitaire);
        return utilitaireMapper.toUtilitaireResponseDTO(returnedUtilitaire);
    }

    @Override
    public List<UtilitaireResponseDTO> findAll() {
        return utilitaireDAO.findAll().stream()
                .map(utilitaire -> utilitaireMapper.toUtilitaireResponseDTO(utilitaire))
                .toList();
    }

    @Override
    public void delete(int id) throws VehiculeException {
        if (utilitaireDAO.existsById(id))
            utilitaireDAO.deleteById(id);
        else
            throw new VehiculeException("ID non trouvée");
    }

    @Override
    public UtilitaireResponseDTO updateFields(int id, UtilitaireRequestDTO utilitaireRequestDTO) throws VehiculeException {
        Optional<Utilitaire> optUtilitaire = utilitaireDAO.findById(id);
        if (optUtilitaire.isEmpty())
            throw new VehiculeException("ID non trouvée");
        checkExistingUtilitaire(utilitaireMapper.toUtilitaire(utilitaireRequestDTO), optUtilitaire.get());
        return utilitaireMapper.toUtilitaireResponseDTO(utilitaireDAO.save(optUtilitaire.get()));
    }

    @Override
    public List<UtilitaireResponseDTO> findVehiculesNotRentedBetween(LocalDate dateDebut, LocalDate dateFin) {
        return utilitaireDAO.findVehiculesNotRentedBetween(dateDebut, dateFin).stream()
                .map(utilitaireMapper::toUtilitaireResponseDTO)
                .toList();
    }

    /*
     * PRIVATE METHODS
     */

    private static void checkExistingUtilitaire(Utilitaire utilitaire, Utilitaire existingUtilitaire) {
        if (utilitaire.getMarque() != null)
            existingUtilitaire.setMarque(utilitaire.getMarque());
        if (utilitaire.getModele() != null)
            existingUtilitaire.setModele(utilitaire.getModele());
        if (utilitaire.getCouleur() != null)
            existingUtilitaire.setCouleur(utilitaire.getCouleur());
        if (utilitaire.getTarif() != null)
            existingUtilitaire.setTarif(utilitaire.getTarif());
        if (utilitaire.getKilometrage() != null)
            existingUtilitaire.setKilometrage(utilitaire.getKilometrage());
        if (utilitaire.getActif() != null)
            existingUtilitaire.setActif(utilitaire.getActif());
        if (utilitaire.getRetire() != null)
            existingUtilitaire.setRetire(utilitaire.getRetire());
        if (utilitaire.getNombrePlaces() != null)
            existingUtilitaire.setNombrePlaces(utilitaire.getNombrePlaces());
        if (utilitaire.getTypeCarburant() != null)
            existingUtilitaire.setTypeCarburant(utilitaire.getTypeCarburant());
        if (utilitaire.getTransmission() != null)
            existingUtilitaire.setTransmission(utilitaire.getTransmission());
        if (utilitaire.getClimatisation() != null)
            existingUtilitaire.setClimatisation(utilitaire.getClimatisation());
        if (utilitaire.getNombrePlaces() != null)
            existingUtilitaire.setNombrePlaces(utilitaire.getNombrePlaces());
        if (utilitaire.getChargeMaximale() != null)
            existingUtilitaire.setChargeMaximale(utilitaire.getChargeMaximale());
        List<Permis> listePermis = new ArrayList<>();
        if (utilitaire.getPoidsPTAC() != null) {
            if (utilitaire.getPoidsPTAC() < 3.5)
                listePermis.add(Permis.B);
            else if (utilitaire.getPoidsPTAC() >= 3.5 && utilitaire.getPoidsPTAC() <= 7.5)
                listePermis.add(Permis.C1);
            else
                throw new VehiculeException("Le poids(PTAC) est invalide");
            existingUtilitaire.setPoidsPTAC(utilitaire.getPoidsPTAC());
        }
        existingUtilitaire.setListePermis(listePermis);
        if (utilitaire.getCapacite() != null)
            existingUtilitaire.setCapacite(utilitaire.getCapacite());
        if (utilitaire.getType() != null)
            existingUtilitaire.setType(utilitaire.getType());
    }

    private static void checkUtilitaire(UtilitaireRequestDTO utilitaireRequestDTO) {
        if (utilitaireRequestDTO == null)
            throw new VehiculeException("L'utilitaire est null");
        if (utilitaireRequestDTO.marque() == null
                || utilitaireRequestDTO.marque().isBlank())
            throw new VehiculeException("La marque est null ou vide");
        if (utilitaireRequestDTO.modele() == null
                || utilitaireRequestDTO.modele().isBlank())
            throw new VehiculeException("Le modele est null ou vide");
        if (utilitaireRequestDTO.couleur() == null
                || utilitaireRequestDTO.couleur().isBlank())
            throw new VehiculeException("La couleur est null ou vide");
        if (utilitaireRequestDTO.tarif() == null)
            throw new VehiculeException("Le tarif est null ou vide");
        if (utilitaireRequestDTO.kilometrage() == null)
            throw new VehiculeException("Le kilométrage est null ou vide");
        if (utilitaireRequestDTO.actif() == null)
            throw new VehiculeException("Le status de location est null");
        if (utilitaireRequestDTO.retire() == null)
            throw new VehiculeException("Le status de validité est null");
        if (utilitaireRequestDTO.nombrePlaces() == null)
            throw new VehiculeException("Le nombre de places est null ou inférieur ou égal à 0");
        if (utilitaireRequestDTO.typeCarburant() == null)
            throw new VehiculeException("Le type de carburant est null");
        if (utilitaireRequestDTO.transmission() == null)
            throw new VehiculeException("La transmission est null");
        if (utilitaireRequestDTO.climatisation() == null)
            throw new VehiculeException("La climatisation est null");
        if (utilitaireRequestDTO.chargeMaximale() == null)
            throw new VehiculeException("La charge maximale est null");
        if (utilitaireRequestDTO.poidsPTAC() == null)
            throw new VehiculeException("Le poids(PTAC) est null");
        if (utilitaireRequestDTO.capacite() == null)
            throw new VehiculeException("La capacité est null");
        if (utilitaireRequestDTO.typeUtilitaire() == null)
            throw new VehiculeException("Le type d'utilitaire est null");
    }
}
