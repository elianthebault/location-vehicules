package com.accenture.controller.vehicule;

import com.accenture.service.dto.vehicule.CampingCarResponseDTO;
import com.accenture.service.dto.vehicule.CampingCarRequestDTO;
import com.accenture.service.vehicule.CampingCarService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("camping-cars")
public class CampingCarController {
    private final CampingCarService campingCarService;

    public CampingCarController(CampingCarService campingCarService) {
        this.campingCarService = campingCarService;
    }

    @GetMapping
    List<CampingCarResponseDTO> findAll() {
        return campingCarService.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<CampingCarResponseDTO> find(@PathVariable("id") int id) {
        CampingCarResponseDTO campingCarResponseDTO = campingCarService.find(id);
        return ResponseEntity.ok(campingCarResponseDTO);
    }

    @PostMapping
    ResponseEntity<Void> save(@RequestBody @Valid CampingCarRequestDTO campingCarRequestDTO) {
        CampingCarResponseDTO savedCampingCar = campingCarService.save(campingCarRequestDTO);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedCampingCar.id())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{id}")
    ResponseEntity<CampingCarResponseDTO> updateFields(@PathVariable("id") int id, @RequestBody CampingCarRequestDTO campingCarRequestDTO) {
        return ResponseEntity.ok(campingCarService.updateFields(id, campingCarRequestDTO));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") int id) {
        campingCarService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
