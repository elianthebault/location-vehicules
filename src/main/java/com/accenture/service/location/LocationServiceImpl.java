package com.accenture.service.location;

import com.accenture.exception.LocationException;
import com.accenture.repository.dao.location.LocationDAO;
import com.accenture.repository.dao.vehicule.VehiculeDAO;
import com.accenture.repository.entity.location.Location;
import com.accenture.repository.entity.vehicule.Vehicule;
import com.accenture.service.dto.location.LocationRequestDTO;
import com.accenture.service.dto.location.LocationResponseDTO;
import com.accenture.service.mapper.location.LocationMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {
    private final LocationDAO locationDAO;
    private final VehiculeDAO vehiculeDAO;
    private final LocationMapper locationMapper;

    public LocationServiceImpl(LocationDAO locationDAO, VehiculeDAO vehiculeDAO, LocationMapper locationMapper) {
        this.locationDAO = locationDAO;
        this.vehiculeDAO = vehiculeDAO;
        this.locationMapper = locationMapper;
    }

    @Override
    public LocationResponseDTO find(int id) throws LocationException {
        Optional<Location> optLocation = locationDAO.findById(id);
        if (optLocation.isEmpty())
            throw new EntityNotFoundException("ID est invalide");
        return locationMapper.toLocationResponseDTO(optLocation.get());
    }

    @Override
    public LocationResponseDTO save(LocationRequestDTO locationRequestDTO) throws LocationException, EntityNotFoundException {
        checkLocation(locationRequestDTO);
        Optional<Vehicule> optVehicule = getVehicule(locationRequestDTO.vehiculeId());
        Location location = locationMapper.toLocation(locationRequestDTO);
        location.setVehicule(optVehicule.get());
        Location returnedLocation = locationDAO.save(location);
        return locationMapper.toLocationResponseDTO(returnedLocation);
    }

    @Override
    public List<LocationResponseDTO> findAll() {
        return locationDAO.findAll().stream()
                .map(locationMapper::toLocationResponseDTO)
                .toList();
    }

    @Override
    public void delete(int id) throws EntityNotFoundException {
        //Faut-il que la location soit terminée pour la supprimer ?
        if (locationDAO.existsById(id))
            locationDAO.deleteById(id);
        else
            throw new EntityNotFoundException("Id non trouvé");
    }

    @Override
    public LocationResponseDTO updateFields(int id, LocationRequestDTO locationRequestDTO) throws EntityNotFoundException {
        Optional<Location> optLocation = locationDAO.findById(id);
        if (optLocation.isEmpty())
            throw new EntityNotFoundException("Id non trouvé");
        Location existingLocation = optLocation.get();
        Location location = locationMapper.toLocation(locationRequestDTO);
        Optional<Vehicule> optVehicule = getVehicule(locationRequestDTO.vehiculeId());
        location.setVehicule(optVehicule.get());
        checkExistingLocation(location, existingLocation);
        return locationMapper.toLocationResponseDTO(locationDAO.save(existingLocation));
    }

    /*
     * PRIVATE METHODS
     */

    private static void checkExistingLocation(Location location, Location existingLocation) {
        if (location.getClient() != null)
            existingLocation.setClient(location.getClient());
        if (location.getVehicule() != null)
            existingLocation.setVehicule(location.getVehicule());
        if (location.getDateDebut() != null)
            existingLocation.setDateDebut(location.getDateDebut());
        if (location.getDateFin() != null)
            existingLocation.setDateFin(location.getDateFin());
        if (location.getKilometresParcourus() != null)
            existingLocation.setKilometresParcourus(location.getKilometresParcourus());
    }

    private Optional<Vehicule> getVehicule(int vehiculeId) {
        Optional<Vehicule> optVehicule = vehiculeDAO.findById(vehiculeId);
        if (optVehicule.isEmpty())
            throw new EntityNotFoundException("ID du véhicule non trouvé");
        return optVehicule;
    }

    private static void checkLocation(LocationRequestDTO locationRequestDTO) {
        if (locationRequestDTO.client() == null)
            throw new LocationException("Le client doit être renseigné.");
        if (locationRequestDTO.vehiculeId() == null)
            throw new LocationException("L'ID du véhicule doit être renseigné.");
        if (locationRequestDTO.dateDebut() == null)
            throw new LocationException("La date du début doit être renseignée.");
        if (locationRequestDTO.dateFin() == null)
            throw new LocationException("La date de fin doit être renseignée.");
        if (locationRequestDTO.kilometresParcourus() == null)
            throw new LocationException("Les kilomètres parcourus doivent être renseignés.");
    }
}
