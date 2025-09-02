package com.ricardo.email.infrastructure.messaging;

import com.ricardo.email.infrastructure.rest.dtos.EmailRecordDto;
import com.ricardo.email.domain.models.Email;
import com.ricardo.email.domain.services.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmailConsumer {

    final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}" )
    public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto) {
        Email emailModel = new Email();
        BeanUtils.copyProperties(emailRecordDto, emailModel);
        emailService.sendEmail(emailModel);
    }
}