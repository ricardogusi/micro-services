package com.ricardo.user.application.port.out;

import com.ricardo.user.models.User;
import java.util.Optional;

public interface UserRepositoryPort {
    User save(User user);
    Optional<User> findById(Long id);
    Optional<User> findByEmail(String email);
}