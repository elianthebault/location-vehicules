package com.accenture.service.vehicule;

import com.accenture.exception.VehiculeException;
import com.accenture.repository.dao.vehicule.MotoDAO;
import com.accenture.repository.entity.vehicule.Moto;
import com.accenture.service.dto.vehicule.MotoResponseDTO;
import com.accenture.service.dto.vehicule.MotoRequestDTO;
import com.accenture.service.mapper.vehicule.MotoMapper;
import com.accenture.shared.enumeration.Permis;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MotoServiceImpl implements MotoService {
    private final MotoDAO motoDAO;
    private final MotoMapper motoMapper;

    public MotoServiceImpl(MotoDAO motoDAO, MotoMapper motoMapper) {
        this.motoDAO = motoDAO;
        this.motoMapper = motoMapper;
    }

    @Override
    public MotoResponseDTO find(int id) throws VehiculeException {
        Optional<Moto> optMoto = motoDAO.findById(id);
        if (optMoto.isEmpty())
            throw new EntityNotFoundException("ID est invalide");
        return motoMapper.toMotoResponseDTO(optMoto.get());
    }

    @Override
    public MotoResponseDTO save(MotoRequestDTO motoRequestDTO) throws VehiculeException {
        checkMoto(motoRequestDTO);
        Moto moto = motoMapper.toMoto(motoRequestDTO);
        //TODO être certain que le client ne puisse pas louer une moto avec un permis "inférieur"
        List<Permis> listePermis = new ArrayList<>();
        if (moto.getCylindree() <= 125  && moto.getPuissanceKW() <= 11) {
            listePermis.add(Permis.A1);
        } else if (moto.getCylindree() > 125  && moto.getPuissanceKW() <= 35) {
            listePermis.add(Permis.A1);
            listePermis.add(Permis.A2);
        } else if (moto.getCylindree() > 125  && moto.getPuissanceKW() > 35) {
            listePermis.add(Permis.A);
            listePermis.add(Permis.A1);
            listePermis.add(Permis.A2);
        }
        moto.setListePermis(listePermis);
        Moto returnedMoto = motoDAO.save(moto);
        return motoMapper.toMotoResponseDTO(returnedMoto);
    }

    @Override
    public List<MotoResponseDTO> findAll() {
        return motoDAO.findAll().stream()
                .map(moto -> motoMapper.toMotoResponseDTO(moto))
                .toList();
    }

    @Override
    public void delete(int id) throws VehiculeException {
        if (motoDAO.existsById(id))
            motoDAO.deleteById(id);
        else
            throw new VehiculeException("ID non trouvée");
    }

    @Override
    public MotoResponseDTO updateFields(int id, MotoRequestDTO motoRequestDTO) throws VehiculeException {
        Optional<Moto> optMoto = motoDAO.findById(id);
        if (optMoto.isEmpty())
            throw new VehiculeException("ID non trouvée");
        checkExistingMoto(motoMapper.toMoto(motoRequestDTO), optMoto.get());
        return motoMapper.toMotoResponseDTO(motoDAO.save(optMoto.get()));
    }

    @Override
    public List<MotoResponseDTO> findVehiculesNotRentedBetween(LocalDate dateDebut, LocalDate dateFin) {
        return motoDAO.findVehiculesNotRentedBetween(dateDebut, dateFin).stream()
                .map(motoMapper::toMotoResponseDTO)
                .toList();
    }

    /*
     * PRIVATE METHODS
     */

    private static void checkExistingMoto(Moto moto, Moto existingMoto) {
        if (moto.getMarque() != null)
            existingMoto.setMarque(moto.getMarque());
        if (moto.getModele() != null)
            existingMoto.setModele(moto.getModele());
        if (moto.getCouleur() != null)
            existingMoto.setCouleur(moto.getCouleur());
        if (moto.getTarif() != null)
            existingMoto.setTarif(moto.getTarif());
        if (moto.getKilometrage() != null)
            existingMoto.setKilometrage(moto.getKilometrage());
        if (moto.getActif() != null)
            existingMoto.setActif(moto.getActif());
        if (moto.getRetire() != null)
            existingMoto.setRetire(moto.getRetire());
        if (moto.getPoids() != null)
            existingMoto.setPoids(moto.getPoids());
        if (moto.getCylindree() != null)
            existingMoto.setCylindree(moto.getCylindree());
        if(moto.getPuissanceKW() != null)
            existingMoto.setPuissanceKW(moto.getPuissanceKW());
        if(moto.getHauteurSelle() != null)
            existingMoto.setHauteurSelle(moto.getHauteurSelle());
        if(moto.getTransmission() != null)
            existingMoto.setTransmission(moto.getTransmission());
        if (moto.getType() != null)
            existingMoto.setType(moto.getType());

        List<Permis> listePermis = new ArrayList<>();
        if (existingMoto.getCylindree() <= 125  && existingMoto.getPuissanceKW() <= 11) {
            listePermis.add(Permis.A1);
        } else if (existingMoto.getCylindree() > 125  && existingMoto.getPuissanceKW() <= 35) {
            listePermis.add(Permis.A1);
            listePermis.add(Permis.A2);
        } else if (existingMoto.getCylindree() > 125  && existingMoto.getPuissanceKW() > 35) {
            listePermis.add(Permis.A);
            listePermis.add(Permis.A1);
            listePermis.add(Permis.A2);
        }
        existingMoto.setListePermis(listePermis);
    }

    private static void checkMoto(MotoRequestDTO motoRequestDTO) {
        if (motoRequestDTO == null)
            throw new VehiculeException("La moto est null");
        if (motoRequestDTO.marque() == null
                || motoRequestDTO.marque().isBlank())
            throw new VehiculeException("La marque est null ou vide");
        if (motoRequestDTO.modele() == null
                || motoRequestDTO.modele().isBlank())
            throw new VehiculeException("Le modele est null ou vide");
        if (motoRequestDTO.couleur() == null
                || motoRequestDTO.couleur().isBlank())
            throw new VehiculeException("La couleur est null ou vide");
        if (motoRequestDTO.tarif() < 0)
            throw new VehiculeException("Le tarif est null ou vide");
        if (motoRequestDTO.kilometrage() < 0)
            throw new VehiculeException("Le kilométrage est null ou vide");
        if (motoRequestDTO.actif() == null)
            throw new VehiculeException("Le status de location est null");
        if (motoRequestDTO.retire() == null)
            throw new VehiculeException("Le status de validité est null");
        if (motoRequestDTO.poids() != null)
            throw new VehiculeException("Le poids est null");
        if (motoRequestDTO.cylindree() != null)
            throw new VehiculeException("Le cylindrée est null");
        if(motoRequestDTO.puissanceKW() != null)
            throw new VehiculeException("La puissance(kW) est null");
        if(motoRequestDTO.hauteurSelle() != null)
            throw new VehiculeException("La hauteur de selle est null");
        if (motoRequestDTO.transmission() == null)
            throw new VehiculeException("La transmission est null");
        if (motoRequestDTO.typeMoto() == null)
            throw new VehiculeException("Le type de moto est null");
    }
}
