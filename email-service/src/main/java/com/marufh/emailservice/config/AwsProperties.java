package com.marufh.emailservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "marufh.email.aws")
public class AwsProperties {
    private String sqsUrl;
    private String accessKey;
    private String secretKey;
    private String regionName;
    private String queueName;
}
