package com.accenture.repository.dao.location;

import com.accenture.repository.entity.location.Location;
import com.accenture.repository.entity.vehicule.Vehicule;
import com.accenture.service.dto.location.LocationResponseDTO;
import com.accenture.shared.enumeration.CategorieVehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LocationDAO extends JpaRepository<Location, Integer> {
}
