package com.ricardo.user.adapters.out.persistence;

import com.ricardo.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SpringDataUserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}