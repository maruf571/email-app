package com.marufh.emailservice.config;

import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway(defaultRequestChannel = "pubSubOutputChannel")
public interface GcloudPubSub {
    void send(String payload);
}
