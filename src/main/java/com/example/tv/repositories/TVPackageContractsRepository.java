package com.example.tv.repositories;

import com.example.tv.entities.TVPackageContracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TVPackageContractsRepository extends JpaRepository<TVPackageContracts, Integer> {

}
