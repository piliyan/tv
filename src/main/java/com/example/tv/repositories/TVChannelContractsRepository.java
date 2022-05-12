package com.example.tv.repositories;

import com.example.tv.entities.TVChannelContracts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TVChannelContractsRepository extends JpaRepository<TVChannelContracts, Integer> {

}
