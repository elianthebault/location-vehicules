package com.accenture.controller.utilisateur;

import com.accenture.service.dto.utilisateur.ClientRequestDTO;
import com.accenture.service.dto.utilisateur.ClientResponseDTO;
import com.accenture.service.utilisateur.ClientService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    List<ClientResponseDTO> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<ClientResponseDTO> find(@PathVariable("id") int id) {
        ClientResponseDTO clientResponseDTO = clientService.find(id);
        return ResponseEntity.ok(clientResponseDTO);
    }

    @PostMapping
    ResponseEntity<Void> save(@RequestBody @Valid ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO savedClient = clientService.save(clientRequestDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedClient.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") int id) {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/login")
    ResponseEntity<ClientResponseDTO> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        ClientResponseDTO clientResponseDTO = clientService.loginClient(email, password);
        return ResponseEntity.ok(clientResponseDTO);
    }

    @GetMapping("/supprimer-compte")
    ResponseEntity<Void> deleteAccount(@RequestParam("email") String email, @RequestParam("password") String password) {
        ClientResponseDTO clientResponseDTO = clientService.loginClient(email, password);
        clientService.delete(clientResponseDTO.id());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PatchMapping("/modifier-compte")
    ResponseEntity<ClientResponseDTO> updateAccount(@RequestParam("email") String email, @RequestParam("password") String password, @RequestBody ClientRequestDTO clientRequestDTO) {
        return ResponseEntity.ok(clientService.updateFields(email, password, clientRequestDTO));
    }
}
