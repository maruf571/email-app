package com.marufh.emailservice.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.outbound.PubSubMessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.MessageHandler;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GcloudPubSubOutBoundConfig {

    private final GcloudProperties gcloudProperties;

    /**
     * MessageHandler(Sender): Create flow from  project -> google pub-sub topic
     * You can add the bean for each topic
     */
    @Bean
    @ServiceActivator(inputChannel = "pubSubOutputChannel")
    public MessageHandler messageSenderEmail(PubSubTemplate pubsubTemplate) {
        return new PubSubMessageHandler(pubsubTemplate, gcloudProperties.getTopicName());
    }
}
