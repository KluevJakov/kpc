package com.example.kpc.repositories;

import com.example.kpc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(@NonNull String email);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO users VALUES(:id,:p1,:p2,:p3,:p4,:p5,:p6,:p7,:p8) ON CONFLICT DO NOTHING",
            nativeQuery = true)
    void insertUser(@Param("id") UUID id,
                    @Param("p1") boolean activated,
                    @Param("p2") String avatar,
                    @Param("p3") Date birthday,
                    @Param("p4") String email,
                    @Param("p5") String fio,
                    @Param("p6") String password,
                    @Param("p7") String phone,
                    @Param("p8") UUID role_id);
}