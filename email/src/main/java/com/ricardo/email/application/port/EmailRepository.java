package com.ricardo.email.application.port;

import com.ricardo.email.domain.models.Email;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailRepository extends JpaRepository<Email, UUID> {
}
