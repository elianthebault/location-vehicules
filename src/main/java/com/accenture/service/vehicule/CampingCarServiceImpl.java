package com.accenture.service.vehicule;

import com.accenture.exception.VehiculeException;
import com.accenture.repository.dao.vehicule.CampingCarDAO;
import com.accenture.repository.entity.vehicule.CampingCar;
import com.accenture.service.dto.vehicule.CampingCarRequestDTO;
import com.accenture.service.dto.vehicule.CampingCarResponseDTO;
import com.accenture.service.mapper.vehicule.CampingCarMapper;
import com.accenture.shared.enumeration.Permis;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CampingCarServiceImpl implements CampingCarService {
    private final CampingCarDAO campingCarDAO;
    private final CampingCarMapper campingCarMapper;

    public CampingCarServiceImpl(CampingCarDAO campingCarDAO, CampingCarMapper campingCarMapper) {
        this.campingCarDAO = campingCarDAO;
        this.campingCarMapper = campingCarMapper;
    }

    @Override
    public CampingCarResponseDTO find(int id) throws VehiculeException {
        Optional<CampingCar> optCampingCar = campingCarDAO.findById(id);
        if (optCampingCar.isEmpty())
            throw new EntityNotFoundException("ID est invalide");
        return campingCarMapper.toCampingCarResponseDTO(optCampingCar.get());
    }

    @Override
    public CampingCarResponseDTO save(CampingCarRequestDTO campingCarRequestDTO) throws VehiculeException {
        checkCampingCar(campingCarRequestDTO);
        CampingCar campingCar = campingCarMapper.toCampingCar(campingCarRequestDTO);
        List<Permis> listePermis = new ArrayList<>();
        if (campingCar.getPoidsPTAC() < 3.5)
            listePermis.add(Permis.B);
        else if (campingCar.getPoidsPTAC() >= 3.5 && campingCar.getPoidsPTAC() <= 7.5)
            listePermis.add(Permis.C1);
        campingCar.setListePermis(listePermis);
        CampingCar returnedCampingCar = campingCarDAO.save(campingCar);
        return campingCarMapper.toCampingCarResponseDTO(returnedCampingCar);
    }

    @Override
    public List<CampingCarResponseDTO> findAll() {
        return campingCarDAO.findAll().stream()
                .map(campingCar -> campingCarMapper.toCampingCarResponseDTO(campingCar))
                .toList();
    }

    @Override
    public void delete(int id) throws VehiculeException {
        if (campingCarDAO.existsById(id))
            campingCarDAO.deleteById(id);
        else
            throw new VehiculeException("ID non trouvée");
    }

    @Override
    public CampingCarResponseDTO updateFields(int id, CampingCarRequestDTO campingCarRequestDTO) throws VehiculeException {
        Optional<CampingCar> optCampingCar = campingCarDAO.findById(id);
        if (optCampingCar.isEmpty())
            throw new VehiculeException("ID non trouvée");
        checkExistingCampingCar(campingCarMapper.toCampingCar(campingCarRequestDTO), optCampingCar.get());
        return campingCarMapper.toCampingCarResponseDTO(campingCarDAO.save(optCampingCar.get()));
    }

    /*
     * PRIVATE METHODS
     */

    private static void checkExistingCampingCar(CampingCar campingCar, CampingCar existingCampingCar) {
        if (campingCar.getMarque() != null)
            existingCampingCar.setMarque(campingCar.getMarque());
        if (campingCar.getModele() != null)
            existingCampingCar.setModele(campingCar.getModele());
        if (campingCar.getCouleur() != null)
            existingCampingCar.setCouleur(campingCar.getCouleur());
        if (campingCar.getTarif() != null)
            existingCampingCar.setTarif(campingCar.getTarif());
        if (campingCar.getKilometrage() != null)
            existingCampingCar.setKilometrage(campingCar.getKilometrage());
        if (campingCar.getActif() != null)
            existingCampingCar.setActif(campingCar.getActif());
        if (campingCar.getRetire() != null)
            existingCampingCar.setRetire(campingCar.getRetire());
        if (campingCar.getNombrePlaces() != null)
            existingCampingCar.setNombrePlaces(campingCar.getNombrePlaces());
        if (campingCar.getTypeCarburant() != null)
            existingCampingCar.setTypeCarburant(campingCar.getTypeCarburant());
        if (campingCar.getTransmission() != null)
            existingCampingCar.setTransmission(campingCar.getTransmission());
        if (campingCar.getClimatisation() != null)
            existingCampingCar.setClimatisation(campingCar.getClimatisation());
        if (campingCar.getNombrePlaces() != null)
            existingCampingCar.setNombrePlaces(campingCar.getNombrePlaces());
        List<Permis> listePermis = new ArrayList<>();
        if (campingCar.getPoidsPTAC() != null) {
            if (campingCar.getPoidsPTAC() < 3.5)
                listePermis.add(Permis.B);
            else if (campingCar.getPoidsPTAC() >= 3.5 && campingCar.getPoidsPTAC() <= 7.5)
                listePermis.add(Permis.C1);
            else
                throw new VehiculeException("Le poids(PTAC) est invalide");
            existingCampingCar.setPoidsPTAC(campingCar.getPoidsPTAC());
            existingCampingCar.setListePermis(listePermis);
        }
        if (campingCar.getHauteur() != null)
            existingCampingCar.setHauteur(campingCar.getHauteur());
        if (campingCar.getNombreCouchages() != null)
            existingCampingCar.setNombreCouchages(campingCar.getNombreCouchages());
        if (campingCar.getCuisineEquipee() != null)
            existingCampingCar.setCuisineEquipee(campingCar.getCuisineEquipee());
        if (campingCar.getLitterie() != null)
            existingCampingCar.setLitterie(campingCar.getLitterie());
        if (campingCar.getRefrigerateur() != null)
            existingCampingCar.setRefrigerateur(campingCar.getRefrigerateur());
        if (campingCar.getDouche() != null)
            existingCampingCar.setDouche(campingCar.getDouche());
        if (campingCar.getTypeCampingCar() != null)
            existingCampingCar.setTypeCampingCar(campingCar.getTypeCampingCar());
    }

    private static void checkCampingCar(CampingCarRequestDTO campingCarRequestDTO) {
        if (campingCarRequestDTO == null)
            throw new VehiculeException("Le camping-car est null");
        if (campingCarRequestDTO.marque() == null
                || campingCarRequestDTO.marque().isBlank())
            throw new VehiculeException("La marque est null ou vide");
        if (campingCarRequestDTO.modele() == null
                || campingCarRequestDTO.modele().isBlank())
            throw new VehiculeException("Le modele est null ou vide");
        if (campingCarRequestDTO.couleur() == null
                || campingCarRequestDTO.couleur().isBlank())
            throw new VehiculeException("La couleur est null ou vide");
        if (campingCarRequestDTO.tarif() == null)
            throw new VehiculeException("Le tarif est null ou vide");
        if (campingCarRequestDTO.kilometrage() == null)
            throw new VehiculeException("Le kilométrage est null ou vide");
        if (campingCarRequestDTO.actif() == null)
            throw new VehiculeException("Le status de location est null");
        if (campingCarRequestDTO.retire() == null)
            throw new VehiculeException("Le status de validité est null");
        if (campingCarRequestDTO.nombrePlaces() == null)
            throw new VehiculeException("Le nombre de places est null ou inférieur ou égal à 0");
        if (campingCarRequestDTO.typeCarburant() == null)
            throw new VehiculeException("Le type de carburant est null");
        if (campingCarRequestDTO.transmission() == null)
            throw new VehiculeException("La transmission est null");
        if (campingCarRequestDTO.climatisation() == null)
            throw new VehiculeException("La climatisation est null");
        if (campingCarRequestDTO.poidsPTAC() == null)
            throw new VehiculeException("Le poids(PTAC) est null");
        if (campingCarRequestDTO.hauteur() == null)
            throw new VehiculeException("La hauteur est null");
        if (campingCarRequestDTO.nombreCouchages() == null)
            throw new VehiculeException("Le nombre de couchages est null");
        if (campingCarRequestDTO.cuisineEquipee() == null)
            throw new VehiculeException("La cuisine équipée est null");
        if (campingCarRequestDTO.litterie() == null)
            throw new VehiculeException("La litterie est null");
        if (campingCarRequestDTO.refrigerateur() == null)
            throw new VehiculeException("Le réfrigérateur est null");
        if (campingCarRequestDTO.douche() == null)
            throw new VehiculeException("La douche est null");
        if (campingCarRequestDTO.typeCampingcar() == null)
            throw new VehiculeException("Le type de camping-car est null");
    }
}
