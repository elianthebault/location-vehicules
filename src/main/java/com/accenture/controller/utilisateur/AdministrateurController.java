package com.accenture.controller.utilisateur;

import com.accenture.service.dto.utilisateur.AdministrateurResponseDTO;
import com.accenture.service.dto.utilisateur.AdministrateurRequestDTO;
import com.accenture.service.utilisateur.AdministrateurService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdministrateurController {
    private final AdministrateurService administrateurService;

    public AdministrateurController(AdministrateurService administrateurService) {
        this.administrateurService = administrateurService;
    }

    @GetMapping
    List<AdministrateurResponseDTO> findAll() {
        return administrateurService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<AdministrateurResponseDTO> find(@PathVariable("id") int id) {
        AdministrateurResponseDTO administrateurResponseDTO = administrateurService.find(id);
        return ResponseEntity.ok(administrateurResponseDTO);
    }

    @PostMapping
    ResponseEntity<Void> save(@RequestBody @Valid AdministrateurRequestDTO administrateurRequestDTO) {
        AdministrateurResponseDTO savedAdministrateur = administrateurService.save(administrateurRequestDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAdministrateur.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") int id) {
        administrateurService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
