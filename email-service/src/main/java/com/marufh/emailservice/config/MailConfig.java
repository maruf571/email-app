package com.marufh.emailservice.config;

import lombok.RequiredArgsConstructor;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MailConfig {

    private final SmtpProperties smtpProperties;

    @Bean
    public Mailer mailer() {
        return MailerBuilder.withSMTPServer(
                smtpProperties.getHost(),
                smtpProperties.getPort(),
                smtpProperties.getUsername(),
                smtpProperties.getPassword()
        ).buildMailer();
    }
}
