package com.ricardo.user.services;

import com.ricardo.user.models.User;
import com.ricardo.user.producers.UserProducer;
import com.ricardo.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    final UserRepository userRepository;
    final UserProducer userProducer;

    public UserService(UserRepository userRepository, UserProducer userProducer) {
        this.userRepository = userRepository;
        this.userProducer = userProducer;
    }

    @Transactional
    public User save(User user) {
        user = userRepository.save(user);
        userProducer.publishMessageEmail(user);
        return user;
    }

}
