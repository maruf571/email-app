package com.marufh.emailservice.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "marufh.email.gcloud")
public class GcloudProperties {
    private String topicName;
    private String subscriptionName;
    private String credentialsLocation;
}
