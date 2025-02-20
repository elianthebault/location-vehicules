package com.accenture.repository.dao.utilisateur;

import com.accenture.repository.entity.utilisateur.Administrateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrateurDAO extends JpaRepository<Administrateur, Integer> {
    boolean existsByEmail(String email);
}
