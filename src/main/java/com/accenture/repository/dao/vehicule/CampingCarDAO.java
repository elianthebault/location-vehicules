package com.accenture.repository.dao.vehicule;

import com.accenture.repository.entity.vehicule.CampingCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampingCarDAO extends JpaRepository<CampingCar, Integer> {
}
