package com.example.tv.repositories;

import com.example.tv.entities.TVChannels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TVChannelsRepository extends JpaRepository<TVChannels, Integer> {
    @Query("SELECT channel FROM TVChannels channel WHERE channel.name = ?1")
    Optional<TVChannels> findTVChannelsByName(String name);
}
