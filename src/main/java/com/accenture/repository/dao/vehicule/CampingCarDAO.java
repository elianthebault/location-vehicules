package com.accenture.repository.dao.vehicule;

import com.accenture.repository.entity.vehicule.CampingCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CampingCarDAO extends JpaRepository<CampingCar, Integer> {
    @Query("""
            SELECT v
                FROM CampingCar v
                WHERE v.actif = true
                  AND v NOT IN (
                      SELECT l.vehicule
                      FROM Location l
                      WHERE l.dateDebut <= :endDate
                        AND l.dateFin >= :startDate
                  )
            """)
    List<CampingCar> findVehiculesNotRentedBetween(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );
}
