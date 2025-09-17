package com.ricardo.user;

import com.ricardo.user.models.User;
import com.ricardo.user.application.port.out.UserRepositoryPort;
import com.ricardo.user.application.port.out.EmailProducerPort;
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
    private UserRepositoryPort userRepositoryPort;

    @Mock
    private EmailProducerPort emailProducerPort;

    @InjectMocks
    private UserService userService;

    @Test
    void whenSaveUser_thenShouldSaveAndPublishMessage() {
        // Arrange
        User user = new User();
        user.setEmail("teste@email.com");
        user.setName("Teste Usuario");

    when(userRepositoryPort.save(any(User.class))).thenReturn(user);
    doNothing().when(emailProducerPort).sendEmail(any());

        // Act
        User savedUser = userService.save(user);

        // Assert
        assertNotNull(savedUser);
        assertEquals("teste@email.com", savedUser.getEmail());
        assertEquals("Teste Usuario", savedUser.getName());
        
    verify(userRepositoryPort, times(1)).save(any(User.class));
    verify(emailProducerPort, times(1)).sendEmail(any());
    }
}