package com.accenture.service.vehicule;

import com.accenture.exception.VehiculeException;
import com.accenture.repository.dao.vehicule.VoitureDAO;
import com.accenture.repository.entity.vehicule.Voiture;
import com.accenture.service.dto.vehicule.VoitureRequestDTO;
import com.accenture.service.dto.vehicule.VoitureResponseDTO;
import com.accenture.service.mapper.vehicule.VoitureMapper;
import com.accenture.shared.enumeration.Permis;
import com.accenture.shared.enumeration.Transmission;
import com.accenture.shared.enumeration.TypeCarburant;
import com.accenture.shared.enumeration.TypeVoiture;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class VoitureServiceImplTest {
    @Mock
    VoitureDAO voitureDAOMock;
    @Mock
    VoitureMapper voitureMapperMock;
    @InjectMocks
    VoitureServiceImpl voitureService;

    @BeforeEach
    void init() {}

    @Test
    void testFindNotExists() {
        Mockito.when(voitureDAOMock.findById(44)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> voitureService.find(44));
        assertEquals("ID est invalide", ex.getMessage());
    }

    @Test
    void testFindExists() {
        Optional<Voiture> optVoiture = Optional.of(createVoiture());
        VoitureResponseDTO voitureResponseDTO = createVoitureResponseDTO();
        Mockito.when(voitureDAOMock.findById(1)).thenReturn(optVoiture);
        Mockito.when(voitureMapperMock.toVoitureResponseDTO(optVoiture.get())).thenReturn(voitureResponseDTO);
        assertSame(voitureResponseDTO, voitureService.find(1));
    }

    @Test
    void testSaveOk() {
        VoitureRequestDTO voitureRequestDTO = createVoitureRequestDTO();
        Voiture voiture = createVoiture();
        Voiture returnedVoiture = createVoiture();
        VoitureResponseDTO expectedResponse = createVoitureResponseDTO();

        Mockito.when(voitureMapperMock.toVoiture(voitureRequestDTO)).thenReturn(voiture);
        Mockito.when(voitureDAOMock.save(voiture)).thenReturn(returnedVoiture);
        Mockito.when(voitureMapperMock.toVoitureResponseDTO(returnedVoiture)).thenReturn(expectedResponse);

        VoitureResponseDTO result = voitureService.save(voitureRequestDTO);

        assertEquals(expectedResponse, result);
        Mockito.verify(voitureDAOMock).save(voiture);
    }

    //TODO test saveFail

    @Test
    void testFindAll() {
        List<Voiture> voitureList = new ArrayList<>();
        voitureList.add(createVoiture());
        voitureList.add(createOtherVoiture());

        List<VoitureResponseDTO> voitureResponseDTOList = new ArrayList<>();
        voitureResponseDTOList.add(createVoitureResponseDTO());
        voitureResponseDTOList.add(createOtherVoitureResponseDTO());

        Mockito.when(voitureDAOMock.findAll()).thenReturn(voitureList);
        Mockito.when(voitureMapperMock.toVoitureResponseDTO(voitureList.getFirst())).thenReturn(voitureResponseDTOList.getFirst());
        Mockito.when(voitureMapperMock.toVoitureResponseDTO(voitureList.get(1))).thenReturn(voitureResponseDTOList.get(1));

        assertEquals(voitureResponseDTOList, voitureService.findAll());
    }

    @Test
    void testDeleteOk() {
        int id = 1;
        Mockito.when(voitureDAOMock.existsById(id)).thenReturn(true);
        Mockito.doNothing().when(voitureDAOMock).deleteById(id);
        voitureService.delete(id);
        Mockito.verify(voitureDAOMock).deleteById(id);
    }

    @Test
    void testDeleteFail() {
        int id = 1;
        Mockito.when(voitureDAOMock.existsById(id)).thenReturn(false);
        assertThrows(VehiculeException.class, () -> voitureService.delete(id));
    }

    @Test
    void testUpdateFieldsOk() {
        int id = 1;
        VoitureRequestDTO voitureRequestDTO = createVoitureRequestDTO();
        Voiture existingVoiture = createVoiture();
        Voiture updateVoiture = createVoiture();
        VoitureResponseDTO expectedVoitureResponse = createOtherVoitureResponseDTO();

        Mockito.when(voitureDAOMock.findById(id)).thenReturn(Optional.of(existingVoiture));
        Mockito.when(voitureMapperMock.toVoiture(voitureRequestDTO)).thenReturn(updateVoiture);
        Mockito.when(voitureDAOMock.save(existingVoiture)).thenReturn((updateVoiture));
        Mockito.when(voitureMapperMock.toVoitureResponseDTO(updateVoiture)).thenReturn(expectedVoitureResponse);

        VoitureResponseDTO resultat = voitureService.updateFields(id, voitureRequestDTO);

        assertEquals(expectedVoitureResponse, resultat);
        Mockito.verify(voitureDAOMock).save(existingVoiture);
    }

    @Test
    void testUpdateFieldsFail() {
        int id = 1;
        VoitureRequestDTO voitureRequestDTO = createVoitureRequestDTO();
        Mockito.when(voitureDAOMock.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> voitureService.updateFields(id, voitureRequestDTO));
    }

    /*
     * PRIVATE METHODS
     */

    private final Voiture createVoiture() {
        Voiture voiture = new Voiture();
        voiture.setId(1);
        voiture.setMarque("Renault");
        voiture.setModele("Twingo");
        voiture.setCouleur("rose");

        List<Permis> listePermis = new ArrayList<>();
        listePermis.add(Permis.B);

        voiture.setListePermis(listePermis);
        voiture.setTarif(15.90);
        voiture.setKilometrage(20000);
        voiture.setActif(true);
        voiture.setRetire(false);
        voiture.setNombrePlaces(5);
        voiture.setTypeCarburant(TypeCarburant.DIESEL);
        voiture.setTransmission(Transmission.MANUELLE);
        voiture.setClimatisation(false);
        voiture.setNombrePortes(3);
        voiture.setNombreBagages(2);
        voiture.setTypeVoiture(TypeVoiture.CITADINE);
        return voiture;
    }

    private final Voiture createOtherVoiture() {
        Voiture voiture = new Voiture();
        voiture.setId(2);
        voiture.setMarque("Citron");
        voiture.setModele("Saxo");
        voiture.setCouleur("citron");

        List<Permis> listePermis = new ArrayList<>();
        listePermis.add(Permis.B);

        voiture.setListePermis(listePermis);
        voiture.setTarif(15.90);
        voiture.setKilometrage(20000);
        voiture.setActif(true);
        voiture.setRetire(false);
        voiture.setNombrePlaces(5);
        voiture.setTypeCarburant(TypeCarburant.DIESEL);
        voiture.setTransmission(Transmission.MANUELLE);
        voiture.setClimatisation(false);
        voiture.setNombrePortes(3);
        voiture.setNombreBagages(2);
        voiture.setTypeVoiture(TypeVoiture.CITADINE);
        return voiture;
    }

    private static VoitureResponseDTO createVoitureResponseDTO() {
        List<Permis> listePermis = new ArrayList<>();
        listePermis.add(Permis.B);
        return new VoitureResponseDTO(
                1,
                "Renault",
                "Twingo",
                "rose",
                listePermis,
                15.90,
                20000,
                true,
                false,
                5,
                TypeCarburant.DIESEL,
                Transmission.MANUELLE,
                false,
                3,
                2,
                TypeVoiture.CITADINE
        );
    }

    private static VoitureResponseDTO createOtherVoitureResponseDTO() {
        List<Permis> listePermis = new ArrayList<>();
        listePermis.add(Permis.B);
        return new VoitureResponseDTO(
                2,
                "Citron",
                "Saxo",
                "citron",
                listePermis,
                15.90,
                20000,
                true,
                false,
                5,
                TypeCarburant.DIESEL,
                Transmission.MANUELLE,
                false,
                3,
                2,
                TypeVoiture.CITADINE
        );
    }

    private static VoitureRequestDTO createVoitureRequestDTO() {
        return new VoitureRequestDTO(
                "Citron",
                "Saxo",
                "citron",
                15.90,
                20000,
                true,
                false,
                5,
                TypeCarburant.DIESEL,
                Transmission.MANUELLE,
                false,
                3,
                2,
                TypeVoiture.CITADINE
        );
    }
}