package com.ricardo.user.producers;

import com.ricardo.user.dtos.EmailRecordDto;
import com.ricardo.user.models.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserProducer {

    final RabbitTemplate rabbitTemplate;

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value(value = "${broker.queue.email.name}")
    private String routingKey;

    public void publishMessageEmail(User user) {
        var emailDto = new EmailRecordDto(
                user.getId(),
                user.getEmail(),
                "Cadastro realizado com sucesso!",
                "Seja bem vindo(a) " + user.getName() + ", agradecemos o seu cadastro, aproveite agora todos os recursos da nossa plataforma!"
        );

        rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }

}
