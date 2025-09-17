
package com.ricardo.user.services;

import com.ricardo.user.models.User;
import com.ricardo.user.application.port.out.UserRepositoryPort;
import com.ricardo.user.application.port.out.EmailProducerPort;
import com.ricardo.user.dtos.EmailRecordDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepositoryPort userRepositoryPort;
    private final EmailProducerPort emailProducerPort;

    public UserService(UserRepositoryPort userRepositoryPort, EmailProducerPort emailProducerPort) {
        this.userRepositoryPort = userRepositoryPort;
        this.emailProducerPort = emailProducerPort;
    }

    @Transactional
    public User save(User user) {
        user = userRepositoryPort.save(user);
        EmailRecordDto emailDto = new EmailRecordDto(
                user.getId(),
                user.getEmail(),
                "Cadastro realizado com sucesso!",
                "Seja bem vindo(a) " + user.getName() + ", agradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma!"
        );
        emailProducerPort.sendEmail(emailDto);
        return user;
    }
}
