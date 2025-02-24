package com.accenture.repository.dao.vehicule;

import com.accenture.repository.entity.vehicule.Moto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MotoDAO extends JpaRepository<Moto, Integer> {
}
