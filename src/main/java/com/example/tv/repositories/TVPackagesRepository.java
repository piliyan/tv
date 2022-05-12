package com.example.tv.repositories;

import com.example.tv.entities.TVPackages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TVPackagesRepository extends JpaRepository<TVPackages, Integer> {
    @Query("SELECT pack FROM TVPackages pack WHERE pack.category = ?1")
    Optional<TVPackages> findTVPackagesByCategory(String category);
}
