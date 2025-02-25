package com.accenture.controller.vehicule;

import com.accenture.service.dto.vehicule.MotoRequestDTO;
import com.accenture.service.dto.vehicule.MotoResponseDTO;
import com.accenture.service.vehicule.MotoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("motos")
public class MotoController {
    private final MotoService motoService;

    public MotoController(MotoService motoService) {
        this.motoService = motoService;
    }

    @GetMapping
    List<MotoResponseDTO> findAll() {
        return motoService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<MotoResponseDTO> find(@PathVariable("id") int id) {
        MotoResponseDTO motoResponseDTO = motoService.find(id);
        return ResponseEntity.ok(motoResponseDTO);
    }

    @PostMapping
    ResponseEntity<Void> save(@RequestBody @Valid MotoRequestDTO motoRequestDTO) {
        MotoResponseDTO savedMoto = motoService.save(motoRequestDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedMoto.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<MotoResponseDTO> updateFields(@PathVariable("id") int id, @RequestBody MotoRequestDTO motoRequestDTO) {
        return ResponseEntity.ok(motoService.updateFields(id, motoRequestDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") int id) {
        motoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
