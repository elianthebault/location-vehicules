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

import java.util.List;
import java.util.Optional;

@Service
public class VoitureServiceImpl implements VoitureService {
    private final VoitureDAO voitureDAO;
    private final VoitureMapper voitureMapper;

    public VoitureServiceImpl(VoitureDAO voitureDAO, VoitureMapper voitureMapper) {
        this.voitureDAO = voitureDAO;
        this.voitureMapper = voitureMapper;
    }

    @Override
    public VoitureResponseDTO find(int id) throws VehiculeException {
        Optional<Voiture> optVoiture = voitureDAO.findById(id);
        if (optVoiture.isEmpty())
            throw new EntityNotFoundException("ID est invalide");
        return voitureMapper.toVoitureResponseDTO(optVoiture.get());
    }

    @Override
    public VoitureResponseDTO save(VoitureRequestDTO voitureRequestDTO) throws VehiculeException {
        checkVoiture(voitureRequestDTO);
        Voiture voiture = voitureMapper.toVoiture(voitureRequestDTO);
        if (voiture.getNombrePlaces() > 0 && voiture.getNombrePlaces() <= 9)
            voiture.setPermis(Permis.B);
        else if (voiture.getNombrePlaces() >= 10 && voiture.getNombrePlaces() <= 16)
            voiture.setPermis(Permis.D1);
        Voiture returnedVoiture = voitureDAO.save(voiture);
        return voitureMapper.toVoitureResponseDTO(returnedVoiture);
    }

    @Override
    public List<VoitureResponseDTO> findAll() {
        return voitureDAO.findAll().stream()
                .map(voiture -> voitureMapper.toVoitureResponseDTO(voiture))
                .toList();
    }

    @Override
    public void delete(int id) throws VehiculeException {
        if (voitureDAO.existsById(id))
            voitureDAO.deleteById(id);
        else
            throw new VehiculeException("ID non trouvée");
    }

    @Override
    public VoitureResponseDTO updateFields(int id, VoitureRequestDTO voitureRequestDTO) throws VehiculeException {
        Optional<Voiture> optVoiture = voitureDAO.findById(id);
        if (optVoiture.isEmpty())
            throw new VehiculeException("ID non trouvée");
        checkExistingVoiture(voitureMapper.toVoiture(voitureRequestDTO), optVoiture.get());
        return voitureMapper.toVoitureResponseDTO(voitureDAO.save(optVoiture.get()));
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
        if (voiture.getNombrePlaces() > 0 && voiture.getNombrePlaces() <= 9) {
            existingVoiture.setNombrePlaces(voiture.getNombrePlaces());
            existingVoiture.setPermis(Permis.B);
        } else if (voiture.getNombrePlaces() >= 10 && voiture.getNombrePlaces() <= 16) {
            existingVoiture.setNombrePlaces(voiture.getNombrePlaces());
            existingVoiture.setPermis(Permis.D1);
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
        if (voiture.getNombrePlaces() > 0)
            existingVoiture.setNombrePlaces(voiture.getNombrePlaces());
        if (voiture.getTypeVoiture() != null)
            existingVoiture.setTypeVoiture(voiture.getTypeVoiture());
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
        if (voitureRequestDTO.nombrePlaces() <= 0)
            throw new VehiculeException("Le nombre de places est null ou inférieur ou égal à 0");
        if (voitureRequestDTO.typeCarburant() == null)
            throw new VehiculeException("Le type de carburant est null");
        if (voitureRequestDTO.transmission() == null)
            throw new VehiculeException("La transmission est null");
        if (voitureRequestDTO.climatisation() == null)
            throw new VehiculeException("La climatisation est null");
        if (voitureRequestDTO.nombrePortes()  != 3
                || voitureRequestDTO.nombrePortes()  != 5)
            throw new VehiculeException("Le nombre de portes est invalide");
        if (voitureRequestDTO.nombreBagages() <= 0)
            throw new VehiculeException("Le nombre de bagages est null ou inférieur ou égal à 0");
        if (voitureRequestDTO.typeVoiture() == null)
            throw new VehiculeException("Le type de voiture est null");
    }
}
