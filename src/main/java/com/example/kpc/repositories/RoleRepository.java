package com.example.kpc.repositories;

import com.example.kpc.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO roles VALUES(:id, :name, :nameLocalization) ON CONFLICT DO NOTHING", nativeQuery = true)
    void insertRole(@Param("id") UUID id,
                    @Param("name") String name,
                    @Param("nameLocalization") String nameLocalization);
}