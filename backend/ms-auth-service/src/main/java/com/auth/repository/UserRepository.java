package com.auth.repository;

import com.auth.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // Método mágico de Spring Data JPA para buscar un usuario por su nombre de usuario
    Optional<UserEntity> findByUsername(String username);
}