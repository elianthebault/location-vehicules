package com.accenture.controller.utilisateur;

import com.accenture.service.dto.utilisateur.ClientRequestDTO;
import com.accenture.service.dto.utilisateur.ClientResponseDTO;
import com.accenture.service.utilisateur.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Slf4j
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

    @GetMapping("${id}")
    ResponseEntity<ClientResponseDTO> find(@PathVariable("id") int id) {
        ClientResponseDTO clientResponseDTO = clientService.find(id);
        return ResponseEntity.ok(clientResponseDTO);
    }

    @PostMapping
    ResponseEntity<Void> save(@RequestBody ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO savedClient = clientService.save(clientRequestDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("${id}")
                .buildAndExpand(savedClient.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("${id}")
    ResponseEntity<Void> delete(@PathVariable("id") int id) {
        clientService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
