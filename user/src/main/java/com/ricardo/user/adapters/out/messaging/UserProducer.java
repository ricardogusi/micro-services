package com.ricardo.user.adapters.out.messaging;

import com.ricardo.user.application.port.out.EmailProducerPort;
import com.ricardo.user.dtos.EmailRecordDto;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class UserProducer implements EmailProducerPort {

    private final RabbitTemplate rabbitTemplate;
    private final String exchange = "email.exchange"; // ajustar conforme config
    private final String routingKey = "email.key"; // ajustar conforme config

    public UserProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendEmail(EmailRecordDto dto) {
        rabbitTemplate.convertAndSend(exchange, routingKey, dto);
    }
}