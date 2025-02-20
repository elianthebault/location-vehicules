package com.accenture.repository.dao.utilisateur;

import com.accenture.repository.entity.utilisateur.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientDAO extends JpaRepository<Client, Integer> {
    boolean existsByEmail(String email);
}
