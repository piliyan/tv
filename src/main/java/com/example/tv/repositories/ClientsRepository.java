package com.example.tv.repositories;

import com.example.tv.entities.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Integer> {
    @Query("SELECT client FROM Clients client WHERE client.egn = ?1")
    Optional<Clients> findClientsByEgn(String egn);
    @Query("SELECT client FROM Clients client WHERE client.phone = ?1")
    Optional<Clients> findClientsByPhone(String egn);
    @Query("SELECT client FROM Clients client WHERE client.email = ?1")
    Optional<Clients> findClientsByEmail(String egn);
}
