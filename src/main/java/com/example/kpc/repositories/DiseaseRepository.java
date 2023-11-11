package com.example.kpc.repositories;

import com.example.kpc.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface DiseaseRepository extends JpaRepository<Disease, UUID> {
    @Transactional
    @Modifying
    @Query("delete from Disease d where d.animal.id = ?1")
    void deleteByAnimalId(UUID animalId);
}