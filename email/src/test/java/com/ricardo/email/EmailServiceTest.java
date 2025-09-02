package com.ricardo.email;

import com.ricardo.email.domain.models.enums.StatusEmail;
import com.ricardo.email.domain.models.Email;
import com.ricardo.email.application.port.EmailRepository;
import com.ricardo.email.domain.services.EmailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmailServiceTest {

    @Mock
    private EmailRepository emailRepository;

    @Mock
    private JavaMailSender emailSender;

    @InjectMocks
    private EmailService emailService;

    @BeforeEach
    void setUp() {
        // Define o valor do emailFrom usando ReflectionTestUtils
        ReflectionTestUtils.setField(emailService, "emailFrom", "from@email.com");
    }

    @Test
    void whenSendEmail_thenShouldSendAndSaveEmail() {
        // Arrange
        Email email = new Email();
        email.setEmailTo("to@email.com");
        email.setSubject("Test Subject");
        email.setText("Test Content");

        when(emailRepository.save(any(Email.class))).thenReturn(email);
        doNothing().when(emailSender).send(any(SimpleMailMessage.class));

        // Act
        Email sentEmail = emailService.sendEmail(email);

        // Assert
        assertNotNull(sentEmail);
        assertEquals("from@email.com", sentEmail.getEmailFrom());
        assertEquals("to@email.com", sentEmail.getEmailTo());
        assertEquals("Test Subject", sentEmail.getSubject());
        assertEquals("Test Content", sentEmail.getText());

        verify(emailRepository, times(1)).save(email);
        verify(emailSender, times(1)).send(any(SimpleMailMessage.class));
    }

    @Test
    void whenSendEmailFails_thenShouldSetErrorStatus() {
        // Arrange
        Email email = new Email();
        email.setEmailTo("to@email.com");
        email.setSubject("Test Subject");
        email.setText("Test Content");

        // Simula o erro no envio do email
        doThrow(new MailException("Erro ao enviar email") {})
                .when(emailSender).send(any(SimpleMailMessage.class));

        when(emailRepository.save(any(Email.class))).thenReturn(email);

        // Act
        Email resultEmail = emailService.sendEmail(email);

        // Assert
        assertEquals(StatusEmail.ERROR, resultEmail.getStatusEmail());
        verify(emailSender, times(1)).send(any(SimpleMailMessage.class));
        verify(emailRepository, times(1)).save(any(Email.class));
    }
}