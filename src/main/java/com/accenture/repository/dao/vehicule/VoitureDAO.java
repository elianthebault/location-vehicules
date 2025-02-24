package com.accenture.repository.dao.vehicule;

import com.accenture.repository.entity.vehicule.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoitureDAO extends JpaRepository<Voiture, Integer> {
}
