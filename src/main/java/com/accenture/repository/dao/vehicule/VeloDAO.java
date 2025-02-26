package com.accenture.repository.dao.vehicule;

import com.accenture.repository.entity.vehicule.Velo;
import com.accenture.repository.entity.vehicule.Voiture;
import com.accenture.service.dto.vehicule.VeloResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface VeloDAO extends JpaRepository<Velo, Integer> {
    @Query("""
            SELECT v
                FROM Velo v
                WHERE v.actif = true
                  AND v NOT IN (
                      SELECT l.vehicule
                      FROM Location l
                      WHERE l.dateDebut <= :endDate
                        AND l.dateFin >= :startDate
                  )
            """)
    List<Velo> findVehiculesNotRentedBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
