package com.accenture.service.vehicule;

import com.accenture.exception.VehiculeException;
import com.accenture.repository.dao.vehicule.VoitureDAO;
import com.accenture.repository.entity.vehicule.Voiture;
import com.accenture.service.dto.vehicule.VoitureRequestDTO;
import com.accenture.service.dto.vehicule.VoitureResponseDTO;
import com.accenture.service.mapper.vehicule.VoitureMapper;
import com.accenture.shared.enumeration.Permis;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service Voiture
 */

@Service
public class VoitureServiceImpl implements VoitureService {
    private final VoitureDAO voitureDAO;
    private final VoitureMapper voitureMapper;

    public VoitureServiceImpl(VoitureDAO voitureDAO, VoitureMapper voitureMapper) {
        this.voitureDAO = voitureDAO;
        this.voitureMapper = voitureMapper;
    }

    /**
     * Permet de trouver une voiture par son ID.
     *
     * @param id
     * @return
     * @throws VehiculeException Si l'ID est invalide.
     */
    @Override
    public VoitureResponseDTO find(int id) throws VehiculeException {
        Optional<Voiture> optVoiture = voitureDAO.findById(id);
        if (optVoiture.isEmpty())
            throw new EntityNotFoundException("ID est invalide");
        return voitureMapper.toVoitureResponseDTO(optVoiture.get());
    }

    /**
     * Permet de sauvegarder une voiture en base de donnée.
     * Le permis est attribué selon les prérequis du client.
     *
     * @param voitureRequestDTO
     * @return
     * @throws VehiculeException par checkVoiture si une information est manquante.
     */
    @Override
    public VoitureResponseDTO save(VoitureRequestDTO voitureRequestDTO) throws VehiculeException {
        checkVoiture(voitureRequestDTO);
        Voiture voiture = voitureMapper.toVoiture(voitureRequestDTO);
        List<Permis> listePermis = new ArrayList<>();
        if (voiture.getNombrePlaces() > 0 && voiture.getNombrePlaces() <= 9)
            listePermis.add(Permis.B);
        else if (voiture.getNombrePlaces() >= 10 && voiture.getNombrePlaces() <= 16)
            listePermis.add(Permis.D1);
        voiture.setListePermis(listePermis);
        Voiture returnedVoiture = voitureDAO.save(voiture);
        return voitureMapper.toVoitureResponseDTO(returnedVoiture);
    }

    /**
     * Renvoi une liste des voitures.
     *
     * @return
     */
    @Override
    public List<VoitureResponseDTO> findAll() {
        return voitureDAO.findAll().stream()
                .map(voiture -> voitureMapper.toVoitureResponseDTO(voiture))
                .toList();
    }

    /**
     * Supprime une voiture selon son ID.
     *
     * @param id
     * @throws VehiculeException Si l'ID est inconnu en base de donnée.
     */
    @Override
    public void delete(int id) throws VehiculeException {
        if (voitureDAO.existsById(id))
            voitureDAO.deleteById(id);
        else
            throw new VehiculeException("ID non trouvée");
    }

    /**
     * Permet de modifier les informations d'une voiture.
     *
     * @param id
     * @param voitureRequestDTO
     * @return
     * @throws VehiculeException Si l'ID de la voiture est inconnu en base de données.
     */
    @Override
    public VoitureResponseDTO updateFields(int id, VoitureRequestDTO voitureRequestDTO) throws EntityNotFoundException {
        Optional<Voiture> optVoiture = voitureDAO.findById(id);
        if (optVoiture.isEmpty())
            throw new EntityNotFoundException("ID non trouvée");
        checkExistingVoiture(voitureMapper.toVoiture(voitureRequestDTO), optVoiture.get());
        return voitureMapper.toVoitureResponseDTO(voitureDAO.save(optVoiture.get()));
    }

    @Override
    public List<VoitureResponseDTO> findVehiculesNotRentedBetween(LocalDate dateDebut, LocalDate dateFin) {
        return voitureDAO.findVehiculesNotRentedBetween(dateDebut, dateFin).stream()
                .map(voitureMapper::toVoitureResponseDTO)
                .toList();
    }

    /*
     * PRIVATE METHODS
     */

    private static void checkExistingVoiture(Voiture voiture, Voiture existingVoiture) {
        if (voiture.getMarque() != null)
            existingVoiture.setMarque(voiture.getMarque());
        if (voiture.getModele() != null)
            existingVoiture.setModele(voiture.getModele());
        if (voiture.getCouleur() != null)
            existingVoiture.setCouleur(voiture.getCouleur());
        if (voiture.getTarif() != null)
            existingVoiture.setTarif(voiture.getTarif());
        if (voiture.getKilometrage() != null)
            existingVoiture.setKilometrage(voiture.getKilometrage());
        if (voiture.getActif() != null)
            existingVoiture.setActif(voiture.getActif());
        if (voiture.getRetire() != null)
            existingVoiture.setRetire(voiture.getRetire());
        List<Permis> listePermis = new ArrayList<>();
        if (voiture.getNombrePlaces() > 0 && voiture.getNombrePlaces() <= 9) {
            existingVoiture.setNombrePlaces(voiture.getNombrePlaces());
            listePermis.add(Permis.B);
        } else if (voiture.getNombrePlaces() >= 10 && voiture.getNombrePlaces() <= 16) {
            existingVoiture.setNombrePlaces(voiture.getNombrePlaces());
            listePermis.add(Permis.D1);
        }
        if (voiture.getTypeCarburant() != null)
            existingVoiture.setTypeCarburant(voiture.getTypeCarburant());
        if (voiture.getTransmission() != null)
            existingVoiture.setTransmission(voiture.getTransmission());
        if (voiture.getClimatisation() != null)
            existingVoiture.setClimatisation(voiture.getClimatisation());
        if (voiture.getNombrePortes() == 3
                || voiture.getNombrePortes() == 5)
            existingVoiture.setNombrePortes(voiture.getNombrePortes());
        else
            throw new VehiculeException("Nombre de portes invalide");
        if (voiture.getNombrePlaces() != null)
            existingVoiture.setNombrePlaces(voiture.getNombrePlaces());
        if (voiture.getTypeVoiture() != null)
            existingVoiture.setTypeVoiture(voiture.getTypeVoiture());

        existingVoiture.setListePermis(listePermis);
    }

    private static void checkVoiture(VoitureRequestDTO voitureRequestDTO) {
        if (voitureRequestDTO == null)
            throw new VehiculeException("La voiture est null");
        if (voitureRequestDTO.marque() == null
                || voitureRequestDTO.marque().isBlank())
            throw new VehiculeException("La marque est null ou vide");
        if (voitureRequestDTO.modele() == null
                || voitureRequestDTO.modele().isBlank())
            throw new VehiculeException("Le modele est null ou vide");
        if (voitureRequestDTO.couleur() == null
                || voitureRequestDTO.couleur().isBlank())
            throw new VehiculeException("La couleur est null ou vide");
        if (voitureRequestDTO.tarif() == null)
            throw new VehiculeException("Le tarif est null ou vide");
        if (voitureRequestDTO.kilometrage() == null)
            throw new VehiculeException("Le kilométrage est null ou vide");
        if (voitureRequestDTO.actif() == null)
            throw new VehiculeException("Le status de location est null");
        if (voitureRequestDTO.retire() == null)
            throw new VehiculeException("Le status de validité est null");
        if (voitureRequestDTO.nombrePlaces() == null)
            throw new VehiculeException("Le nombre de places est null ou inférieur ou égal à 0");
        if (voitureRequestDTO.typeCarburant() == null)
            throw new VehiculeException("Le type de carburant est null");
        if (voitureRequestDTO.transmission() == null)
            throw new VehiculeException("La transmission est null");
        if (voitureRequestDTO.climatisation() == null)
            throw new VehiculeException("La climatisation est null");
        if (voitureRequestDTO.nombrePortes()  != 3
                && voitureRequestDTO.nombrePortes()  != 5)
            throw new VehiculeException("Le nombre de portes est invalide");
        if (voitureRequestDTO.nombreBagages() <= 0)
            throw new VehiculeException("Le nombre de bagages est null ou inférieur ou égal à 0");
        if (voitureRequestDTO.typeVoiture() == null)
            throw new VehiculeException("Le type de voiture est null");
    }
}
