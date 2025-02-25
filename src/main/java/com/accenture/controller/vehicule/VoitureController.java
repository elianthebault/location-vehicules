package com.accenture.controller.vehicule;

import com.accenture.service.dto.vehicule.VoitureRequestDTO;
import com.accenture.service.dto.vehicule.VoitureResponseDTO;
import com.accenture.service.vehicule.VoitureService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/voitures")
public class VoitureController {
    private final VoitureService voitureService;

    public VoitureController(VoitureService voitureService) {
        this.voitureService = voitureService;
    }

    @GetMapping
    List<VoitureResponseDTO> findAll() {
        return voitureService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<VoitureResponseDTO> find(@PathVariable("id") int id) {
        VoitureResponseDTO voitureResponseDTO = voitureService.find(id);
        return ResponseEntity.ok(voitureResponseDTO);
    }

    @PostMapping
    ResponseEntity<Void> save(@RequestBody @Valid VoitureRequestDTO voitureRequestDTO) {
        VoitureResponseDTO savedVoiture = voitureService.save(voitureRequestDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedVoiture.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<VoitureResponseDTO> updateFields(@PathVariable("id") int id, @RequestBody VoitureRequestDTO voitureRequestDTO) {
        return ResponseEntity.ok(voitureService.updateFields(id, voitureRequestDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") int id) {
        voitureService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
