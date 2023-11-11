package com.example.kpc.repositories;

import com.example.kpc.entity.Inspect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface InspectRepository extends JpaRepository<Inspect, UUID> {
    @Transactional
    @Modifying
    @Query("delete from Inspect i where i.animal.id = ?1")
    void deleteByAnimalId(UUID animalId);
}