package com.accenture.service.utilisateur;

import com.accenture.exception.UtilisateurException;
import com.accenture.repository.dao.utilisateur.ClientDAO;
import com.accenture.repository.entity.utilisateur.Adresse;
import com.accenture.repository.entity.utilisateur.Client;
import com.accenture.service.dto.utilisateur.AdresseRequestDTO;
import com.accenture.service.dto.utilisateur.AdresseResponseDTO;
import com.accenture.service.dto.utilisateur.ClientRequestDTO;
import com.accenture.service.dto.utilisateur.ClientResponseDTO;
import com.accenture.service.mapper.utilisateur.ClientMapper;
import com.accenture.shared.enumeration.Permis;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {
    @Mock
    ClientDAO clientDAOMock;
    @Mock
    ClientMapper clientMapperMock;
    @Mock
    PasswordEncoder passwordEncoderMock;
    @InjectMocks
    ClientServiceImpl clientService;

    @BeforeEach
    void init() {}

    @Test
    void TestFindNotExists() {
        Mockito.when(clientDAOMock.findById(44)).thenReturn(Optional.empty());
        EntityNotFoundException ex = assertThrows(EntityNotFoundException.class, () -> clientService.find(44));
        assertEquals("ID est invalide", ex.getMessage());
    }

    @Test
    void testFindExists() {
        Optional<Client> optClient = Optional.of(createClient());
        ClientResponseDTO clientResponseDTO = createClientResponseDTO();
        Mockito.when(clientDAOMock.findById(1)).thenReturn(optClient);
        Mockito.when(clientMapperMock.toClientResponseDTO(optClient.get())).thenReturn(clientResponseDTO);
        assertSame(clientResponseDTO, clientService.find(1));
    }

    @Test
    void testFindAll() {
        Client client = createClient();
        Client otherClient = createOtherClient();
        List<Client> clientList = List.of(client, otherClient);
        ClientResponseDTO clientResponseDTO = createClientResponseDTO();
        ClientResponseDTO otherClientResponseDTO = createOtherClientResponseDTO();
        List<ClientResponseDTO> clientResponseDTOList = List.of(clientResponseDTO, otherClientResponseDTO);
        Mockito.when(clientDAOMock.findAll()).thenReturn(clientList);
        Mockito.when(clientMapperMock.toClientResponseDTO(client)).thenReturn(clientResponseDTO);
        Mockito.when(clientMapperMock.toClientResponseDTO(otherClient)).thenReturn(otherClientResponseDTO);
        assertEquals(clientResponseDTOList, clientService.findAll());
    }

    @DisplayName("Client == null")
    @Test
    void testSaveNull() {
        assertThrows(UtilisateurException.class, () -> clientService.save(null));
    }

    @DisplayName("nom == null")
    @Test
    void testSaveNomNull() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO(
                null,
                "allo",
                "allo@mail.fr",
                "@Allo12345",
                new AdresseRequestDTO(
                        "11 avenue de Bretagne",
                        "44400",
                        "REZE"
                ),
                LocalDate.of(1986, 9, 7),
                List.of(Permis.A, Permis.B)
        );
        assertThrows(UtilisateurException.class, () -> clientService.save(clientRequestDTO));
    }

    @DisplayName("prenom == null")
    @Test
    void testSavePrenomNull() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO(
                "allo",
                null,
                "allo@mail.fr",
                "@Allo12345",
                new AdresseRequestDTO(
                        "11 avenue de Bretagne",
                        "44400",
                        "REZE"
                ),
                LocalDate.of(1986, 9, 7),
                List.of(Permis.A, Permis.B)
        );
        assertThrows(UtilisateurException.class, () -> clientService.save(clientRequestDTO));
    }

    @DisplayName("email == null")
    @Test
    void testSaveEmailNull() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO(
                "allo",
                "allo",
                null,
                "@Allo12345",
                new AdresseRequestDTO(
                        "11 avenue de Bretagne",
                        "44400",
                        "REZE"
                ),
                LocalDate.of(1986, 9, 7),
                List.of(Permis.A, Permis.B)
        );
        assertThrows(UtilisateurException.class, () -> clientService.save(clientRequestDTO));
    }

    @Test
    void testSaveEmailAlreadyUsed() {
        String existingEmail = "allo@mail.fr";
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO(
                "allo",
                "allo",
                existingEmail,
                "@Allo12345",
                new AdresseRequestDTO("11 avenue de Bretagne", "4440", "REZE"),
                LocalDate.of(1986, 9, 7),
                List.of(Permis.A, Permis.B)
        );
        Client existingClient = createClient();
        existingClient.setEmail(existingEmail);
        Mockito.when(clientDAOMock.existsByEmail(existingEmail)).thenReturn(true);
        assertThrows(IllegalArgumentException.class, () -> {
            clientService.save(clientRequestDTO);
        }, "Cet email est déjà utilisé");
    }

    @DisplayName("password == null")
    @Test
    void testSavePasswordNull() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO(
                "allo",
                "allo",
                "allo@mail.fr",
                null,
                new AdresseRequestDTO(
                        "11 avenue de Bretagne",
                        "44400",
                        "REZE"
                ),
                LocalDate.of(1986, 9, 7),
                List.of(Permis.A, Permis.B)
        );
        assertThrows(UtilisateurException.class, () -> clientService.save(clientRequestDTO));
    }

    @DisplayName("Adresse == null")
    @Test
    void testSaveAdresseNull() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO(
                "allo",
                "allo",
                "allo@mail.fr",
                "@Allo12345",
                null,
                LocalDate.of(1986, 9, 7),
                List.of(Permis.A, Permis.B)
        );
        assertThrows(UtilisateurException.class, () -> clientService.save(clientRequestDTO));
    }

    @DisplayName("Adresse.adresse == null")
    @Test
    void testSaveAdresseAdresseNull() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO(
                "allo",
                "allo",
                "allo@mail.fr",
                "@Allo12345",
                new AdresseRequestDTO(
                        null,
                        "44400",
                        "REZE"
                ),
                LocalDate.of(1986, 9, 7),
                List.of(Permis.A, Permis.B)
        );
        assertThrows(UtilisateurException.class, () -> clientService.save(clientRequestDTO));
    }

    @DisplayName("Adresse.codePostal == null")
    @Test
    void testSaveAdresseCodePostalNull() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO(
                "allo",
                "allo",
                "allo@mail.fr",
                "@Allo12345",
                new AdresseRequestDTO(
                        "11 avenue de Bretagne",
                        null,
                        "REZE"
                ),
                LocalDate.of(1986, 9, 7),
                List.of(Permis.A, Permis.B)
        );
        assertThrows(UtilisateurException.class, () -> clientService.save(clientRequestDTO));
    }

    @DisplayName("Adresse.ville == null")
    @Test
    void testSaveAdresseVilleNull() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO(
                "allo",
                "allo",
                "allo@mail.fr",
                "@Allo12345",
                new AdresseRequestDTO(
                        "11 avenue de Bretagne",
                        "44400",
                        null
                ),
                LocalDate.of(1986, 9, 7),
                List.of(Permis.A, Permis.B)
        );
        assertThrows(UtilisateurException.class, () -> clientService.save(clientRequestDTO));
    }

    @DisplayName("dateNaissance == null")
    @Test
    void testSaveDateNaissanceNull() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO(
                "allo",
                "allo",
                "allo@mail.fr",
                "@Allo12345",
                new AdresseRequestDTO(
                        "11 avenue de Bretagne",
                        "44400",
                        "REZE"
                ),
                null,
                List.of(Permis.A, Permis.B)
        );
        assertThrows(UtilisateurException.class, () -> clientService.save(clientRequestDTO));
    }

    @DisplayName("listePermis == null")
    @Test
    void testSaveListePermisNull() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO(
                "allo",
                "allo",
                "allo@mail.fr",
                "@Allo12345",
                new AdresseRequestDTO(
                        "11 avenue de Bretagne",
                        "44400",
                        "REZE"
                ),
                LocalDate.of(1986, 9, 7),
                null
        );
        assertThrows(UtilisateurException.class, () -> clientService.save(clientRequestDTO));
    }

    @Test
    void testSaveOk() {
        ClientRequestDTO clientRequestDTO = new ClientRequestDTO(
                "allo",
                "allo",
                "allo@mail.fr",
                "@Allo12345",
                new AdresseRequestDTO(
                        "11 avenue de Bretagne",
                        "44400",
                        "REZE"
                ),
                LocalDate.of(1986, 9, 7),
                List.of(Permis.A, Permis.B)
        );
        Client clientBeforeSave = createClient();
        clientBeforeSave.setId(0);
        Client clientAfterSave = createClient();
        ClientResponseDTO clientResponseDTO = createClientResponseDTO();
        Mockito.when(clientMapperMock.toClient(clientRequestDTO)).thenReturn(clientBeforeSave);
        Mockito.when(clientDAOMock.save(clientBeforeSave)).thenReturn(clientAfterSave);
        Mockito.when(clientMapperMock.toClientResponseDTO(clientAfterSave)).thenReturn(clientResponseDTO);
        assertSame(clientResponseDTO, clientService.save(clientRequestDTO));
        Mockito.verify(clientDAOMock, Mockito.times(1)).save(clientBeforeSave);
    }

    @Test
    void checkLoginSuccess() {
        String email = "allo@mail.fr";
        String password = "@Allo12345";
        Optional<Client> optClient = Optional.of(createClient());
        Mockito.when(passwordEncoderMock.matches("@Allo12345", optClient.get().getPassword())).thenReturn(true);
        Mockito.when(clientDAOMock.findByEmail(email)).thenReturn(optClient);
        assertSame(optClient, clientService.checkLogin(email, password));
    }

    @Test
    void checkLoginEmailFailed() {
        Optional<Client> optClient = Optional.empty();
        String email = "allo@mail.fr";
        String password = "@Allo12345";
        Mockito.when(clientDAOMock.findByEmail(email)).thenReturn(optClient);
        assertThrows(EntityNotFoundException.class, () -> clientService.checkLogin(email, password));
    }

    @Test
    void checkLoginPasswordFailed() {
        String email = "allo@mail.fr";
        String password = "@Allo12345";
        Optional<Client> optClient = Optional.of(createClient());
        Mockito.when(passwordEncoderMock.matches("@Allo12345", optClient.get().getPassword())).thenReturn(false);
        Mockito.when(clientDAOMock.findByEmail(email)).thenReturn(optClient);
        assertThrows(UtilisateurException.class, () -> clientService.checkLogin(email, password));
    }

    @Test
    void loginClientSuccess() {
        // Arrange
        String email = "allo@mail.fr";
        String password = "@Allo12345";
        Client client = createClient();
        Optional<Client> optClient = Optional.of(client);
        ClientResponseDTO expectedResponse = clientMapperMock.toClientResponseDTO(optClient.get());

        // Mock the behavior of clientDAO and passwordEncoder
        Mockito.when(clientDAOMock.findByEmail(email)).thenReturn(optClient);
        Mockito.when(passwordEncoderMock.matches(password, client.getPassword())).thenReturn(true);
        Mockito.when(clientMapperMock.toClientResponseDTO(client)).thenReturn(expectedResponse);

        // Act
        ClientResponseDTO result = clientService.loginClient(email, password);

        // Assert
        assertEquals(expectedResponse, result);
    }

    @Test
    void testDeleteFail() {
        assertThrows(UtilisateurException.class, () -> clientService.delete(44));
    }

    //TODO Test updateFields()

    /*
     * PRIVATE METHODS
     */

    private ClientRequestDTO createClientRequestDTO() {
        return new ClientRequestDTO(
                "allo",
                "allo",
                "allo@mail.fr",
                "@Allo12345",
                new AdresseRequestDTO(
                        "11 avenue de Bretagne",
                        "44400",
                        "REZE"
                ),
                LocalDate.of(1986, 9, 7),
                List.of(Permis.A, Permis.B)
        );
    }

    private Client createClient() {
        Client client = new Client();
        client.setId(1);
        client.setNom("allo");
        client.setPrenom("allo");
        client.setEmail("allo@mail.fr");
        client.setPassword("@Allo12345");
        client.setRole("ROLE_CLIENT");
        client.setAdresse(new Adresse(1, "11 avenue de Bretagne", "44400", "REZE"));
        client.setDateNaissance(LocalDate.of(1986, 9, 7));
        client.setDateInscription(LocalDate.of(2025, 12, 12));
        client.setListePermis(List.of(Permis.A, Permis.B));
        client.setDesactive(false);
        return client;
    }

    private Client createOtherClient() {
        Client client = new Client();
        client.setId(2);
        client.setNom("aluile");
        client.setPrenom("aluile");
        client.setEmail("aluile@mail.fr");
        client.setPassword("@Allo12345");
        client.setRole("ROLE_CLIENT");
        client.setAdresse(new Adresse(2, "11 avenue de Bretagne", "4440", "REZE"));
        client.setDateNaissance(LocalDate.of(1986, 9, 7));
        client.setDateInscription(LocalDate.of(2025, 12, 12));
        client.setListePermis(List.of(Permis.A, Permis.B));
        client.setDesactive(false);
        return client;
    }

    private static ClientResponseDTO createClientResponseDTO() {
        return new ClientResponseDTO(
            1,
            "allo",
            "allo",
            "allo@mail.fr",
            "@Allo12345",
            "ROLE_CLIENT",
            new AdresseResponseDTO(1, "11 avenue de Bretagne", "44400", "REZE"),
            LocalDate.of(1986, 9, 7),
            LocalDate.of(2025, 12, 12),
            List.of(Permis.A, Permis.B),
            false
        );
    }

    private static ClientResponseDTO createOtherClientResponseDTO() {
        return new ClientResponseDTO(
                2,
                "aluile",
                "aluile",
                "aluile@mail.fr",
                "@Allo12345",
                "ROLE_CLIENT",
                new AdresseResponseDTO(2, "11 avenue de Bretagne", "4440", "REZE"),
                LocalDate.of(1986, 9, 7),
                LocalDate.of(2025, 12, 12),
                List.of(Permis.A, Permis.B),
                false
        );
    }
}