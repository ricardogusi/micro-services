package com.ricardo.user.application.port.out;

import com.ricardo.user.dtos.EmailRecordDto;

public interface EmailProducerPort {
    void sendEmail(EmailRecordDto dto);
}