package com.accenture.controller.vehicule;

import com.accenture.service.dto.vehicule.VeloRequestDTO;
import com.accenture.service.dto.vehicule.VeloResponseDTO;
import com.accenture.service.vehicule.VeloService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/velos")
public class VeloController {
    private final VeloService veloService;

    public VeloController(VeloService veloService) {
        this.veloService = veloService;
    }

    @GetMapping
    List<VeloResponseDTO> findAll() {
        return veloService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<VeloResponseDTO> find(@PathVariable("id") int id) {
        VeloResponseDTO veloResponseDTO = veloService.find(id);
        return ResponseEntity.ok(veloResponseDTO);
    }

    @PostMapping
    ResponseEntity<Void> save(@RequestBody @Valid VeloRequestDTO veloRequestDTO) {
        VeloResponseDTO savedVelo = veloService.save(veloRequestDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedVelo.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<VeloResponseDTO> updateFields(@PathVariable("id") int id, @RequestBody VeloRequestDTO veloRequestDTO) {
        return ResponseEntity.ok(veloService.updateFields(id, veloRequestDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") int id) {
        veloService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
