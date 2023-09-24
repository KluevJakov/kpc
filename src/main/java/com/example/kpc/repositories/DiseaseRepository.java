package com.example.kpc.repositories;

import com.example.kpc.entity.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DiseaseRepository extends JpaRepository<Disease, UUID> {
}