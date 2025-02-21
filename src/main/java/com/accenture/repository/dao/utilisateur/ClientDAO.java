package com.accenture.repository.dao.utilisateur;

import com.accenture.repository.entity.utilisateur.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientDAO extends JpaRepository<Client, Integer> {
    boolean existsByEmail(String email);
    Optional<Client> findByEmail(String email);
}
