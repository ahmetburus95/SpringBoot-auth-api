package com.backend.register_login.repository; // Paket adını Repository yapmak daha düzenli olur

import com.backend.register_login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface AuthUserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);

    boolean existsByName(String name);
    boolean existsByEmail(String email);

}