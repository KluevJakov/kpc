package com.example.kpc.repositories;

import com.example.kpc.entity.Inspect;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface InspectRepository extends JpaRepository<Inspect, UUID> {
}