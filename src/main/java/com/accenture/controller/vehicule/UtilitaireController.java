package com.accenture.controller.vehicule;

import com.accenture.service.dto.vehicule.UtilitaireRequestDTO;
import com.accenture.service.dto.vehicule.UtilitaireResponseDTO;
import com.accenture.service.vehicule.UtilitaireService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("utilitaires")
public class UtilitaireController {
    private final UtilitaireService utilitaireService;

    public UtilitaireController(UtilitaireService utilitaireService) {
        this.utilitaireService = utilitaireService;
    }

    @GetMapping
    List<UtilitaireResponseDTO> findAll() {
        return utilitaireService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<UtilitaireResponseDTO> find(@PathVariable("id") int id) {
        UtilitaireResponseDTO utilitaireResponseDTO = utilitaireService.find(id);
        return ResponseEntity.ok(utilitaireResponseDTO);
    }

    @PostMapping
    ResponseEntity<Void> save(@RequestBody @Valid UtilitaireRequestDTO utilitaireRequestDTO) {
        UtilitaireResponseDTO savedUtilitaire = utilitaireService.save(utilitaireRequestDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUtilitaire.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<UtilitaireResponseDTO> updateFields(@PathVariable("id") int id, @RequestBody UtilitaireRequestDTO utilitaireRequestDTO) {
        return ResponseEntity.ok(utilitaireService.updateFields(id, utilitaireRequestDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") int id) {
        utilitaireService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
