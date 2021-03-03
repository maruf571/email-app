package com.marufh.emailservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "marufh.email.smtp")
public class SmtpProperties {
    private String host;
    private Integer port;
    private String username;
    private String password;
}


