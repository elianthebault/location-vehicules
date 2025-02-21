package com.accenture.repository.dao.utilisateur;

import com.accenture.repository.entity.utilisateur.Administrateur;
import com.accenture.repository.entity.utilisateur.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministrateurDAO extends JpaRepository<Administrateur, Integer> {
    boolean existsByEmail(String email);
    Optional<Administrateur> findByEmail(String email);
}
