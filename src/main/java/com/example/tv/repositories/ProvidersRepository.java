package com.example.tv.repositories;

import com.example.tv.entities.Providers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvidersRepository extends JpaRepository<Providers, Integer> {
    @Query("SELECT pr FROM Providers pr WHERE pr.name = ?1")
    Optional<Providers> findProvidersByName(String name);
}
