package com.accenture.repository.dao.utilisateur;

import com.accenture.repository.entity.utilisateur.Adresse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresseDAO extends JpaRepository<Adresse, Integer> {
}
