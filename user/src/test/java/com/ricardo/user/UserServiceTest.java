package com.ricardo.user;

import com.ricardo.user.models.User;
import com.ricardo.user.producers.UserProducer;
import com.ricardo.user.repository.UserRepository;
import com.ricardo.user.services.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserProducer userProducer;

    @InjectMocks
    private UserService userService;

    @Test
    void whenSaveUser_thenShouldSaveAndPublishMessage() {
        // Arrange
        User user = new User();
        user.setEmail("teste@email.com");
        user.setName("Teste Usuario");

        when(userRepository.save(any(User.class))).thenReturn(user);
        doNothing().when(userProducer).publishMessageEmail(any(User.class));

        // Act
        User savedUser = userService.save(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals("teste@email.com", savedUser.getEmail());
        assertEquals("Teste Usuario", savedUser.getName());
        
        verify(userRepository, times(1)).save(any(User.class));
        verify(userProducer, times(1)).publishMessageEmail(any(User.class));
    }
}