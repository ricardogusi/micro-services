package com.ricardo.email.infrastructure.rest.dtos;

import java.util.UUID;

public record EmailRecordDto(UUID userId, String emailTo, String subject, String text) {
}