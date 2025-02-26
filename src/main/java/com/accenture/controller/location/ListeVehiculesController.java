package com.accenture.controller.location;

import com.accenture.service.dto.vehicule.*;
import com.accenture.service.vehicule.*;
import com.accenture.shared.enumeration.CategorieVehicule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

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

    @GetMapping("/search")
    ListeVehiculesDTO search(
            @RequestParam(required = false) LocalDate dateDebut,
            @RequestParam(required = false) LocalDate dateFin,
            @RequestParam(required = false) CategorieVehicule categorieVehicule
    ) {
        if (categorieVehicule != null)
            return new ListeVehiculesDTO(
                    categorieVehicule == CategorieVehicule.VOITURE ? voitureService.findVehiculesNotRentedBetween(dateDebut, dateFin) : null,
                    categorieVehicule == CategorieVehicule.UTILITAIRE ? utilitaireService.findVehiculesNotRentedBetween(dateDebut, dateFin) : null,
                    categorieVehicule == CategorieVehicule.CAMPINGCAR ? campingCarService.findVehiculesNotRentedBetween(dateDebut, dateFin) : null,
                    categorieVehicule == CategorieVehicule.MOTO ? motoService.findVehiculesNotRentedBetween(dateDebut, dateFin) : null,
                    categorieVehicule == CategorieVehicule.VELO ? veloService.findVehiculesNotRentedBetween(dateDebut, dateFin) : null
            );
        else
            return new ListeVehiculesDTO(
                    voitureService.findVehiculesNotRentedBetween(dateDebut, dateFin),
                    utilitaireService.findVehiculesNotRentedBetween(dateDebut, dateFin),
                    campingCarService.findVehiculesNotRentedBetween(dateDebut, dateFin),
                    motoService.findVehiculesNotRentedBetween(dateDebut, dateFin),
                    veloService.findVehiculesNotRentedBetween(dateDebut, dateFin)
            );
    }
}
