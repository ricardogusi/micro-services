package com.ricardo.user.adapters.out.persistence;

import com.ricardo.user.application.port.out.UserRepositoryPort;
import com.ricardo.user.models.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class SpringDataUserRepository implements UserRepositoryPort {

    private final SpringDataUserJpaRepository jpa;

    public SpringDataUserRepository(SpringDataUserJpaRepository jpa) {
        this.jpa = jpa;
    }

    @Override
    public User save(User user) {
        return jpa.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return jpa.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return jpa.findByEmail(email);
    }
}