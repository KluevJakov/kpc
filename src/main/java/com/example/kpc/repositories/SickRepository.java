package com.example.kpc.repositories;

import com.example.kpc.entity.Sick;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SickRepository extends JpaRepository<Sick, UUID> {
}