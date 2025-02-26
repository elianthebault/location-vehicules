package com.accenture.controller.location;

import com.accenture.service.dto.vehicule.*;
import com.accenture.service.vehicule.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/listes")
public class ListeVehiculesController {
    private final VoitureService voitureService;
    private final MotoService motoService;
    private final UtilitaireService utilitaireService;
    private final VeloService veloService;
    private final CampingCarService campingCarService;

    public ListeVehiculesController(VoitureService voitureService, MotoService motoService, UtilitaireService utilitaireService, VeloService veloService, CampingCarService campingCarService) {
        this.voitureService = voitureService;
        this.motoService = motoService;
        this.utilitaireService = utilitaireService;
        this.veloService = veloService;
        this.campingCarService = campingCarService;
    }

    @GetMapping
    ListeVehiculesDTO listeVehicules() {
        return new ListeVehiculesDTO(
                voitureService.findAll(),
                utilitaireService.findAll(),
                campingCarService.findAll(),
                motoService.findAll(),
                veloService.findAll()
        );
    }
}
