package com.marufh.emailservice.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marufh.emailclient.model.BaseEmail;
import com.marufh.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gcp.pubsub.core.PubSubTemplate;
import org.springframework.cloud.gcp.pubsub.integration.AckMode;
import org.springframework.cloud.gcp.pubsub.integration.inbound.PubSubInboundChannelAdapter;
import org.springframework.cloud.gcp.pubsub.support.BasicAcknowledgeablePubsubMessage;
import org.springframework.cloud.gcp.pubsub.support.GcpPubSubHeaders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;


/**
 * ============= How pabSub works with Spring integration =========
 * There are two types of communication here
 * 1. Inbound: google pub-sub  subscriber -> project
 * 2. Outbound: project -> google pub-sub topic
 *
 * MessageChannel: Responsible to create channel for inbound communication
 *
 * PubSubOutboundGateway: Responsible to create outbound communication
 *
 * There are two types of message handler, work based on ServiceActivator
 * 1. If ServiceActivator 'MessageChannel' type, means it is inbound message handler
 * 2. If ServiceActivator 'PubSubOutboundGateway' type. it is outbound message handler
 *
 * MessageChannel, PubSubInboundChannelAdapter & MessageHandler works together for a specific subscriber.
 */

@Slf4j
@Configuration
@RequiredArgsConstructor
public class GcloudPubSubInInBoundConfig {

    private final GcloudProperties gcloudProperties;
    private final ObjectMapper objectMapper;
    private final EmailService emailService;

    /**
     * PubSub Input Channel: This is the main bean to communicate with google pub-sub.
     * This message channel is used for both inbound and outbound adapter
     */
    @Bean(name = "pubSubInputChannel")
    public MessageChannel pubSubInputChannel1() {
        return new DirectChannel();
    }

    /**
     *  PubSubInboundChannelAdapter: Create flow from google pubs-ub subscription ->  project
     */
    @Bean
    public PubSubInboundChannelAdapter messageChannelAdapter(
            @Qualifier("pubSubInputChannel") MessageChannel inputChannel,
            PubSubTemplate pubSubTemplate) {
        PubSubInboundChannelAdapter adapter = new PubSubInboundChannelAdapter(
                pubSubTemplate, gcloudProperties.getSubscriptionName()
        );
        adapter.setOutputChannel(inputChannel);
        adapter.setAckMode(AckMode.MANUAL);
        return adapter;
    }


    /**
     * MessageHandler(Receiver): Receive message from pub-sub subscriber
     */
    @Bean
    @ServiceActivator(inputChannel = "pubSubInputChannel")
    public MessageHandler messageReceiver() {

        return message -> {
            String jsonString = new String((byte[]) message.getPayload());
            log.info("Message arrived! Payload: " + jsonString);
            BasicAcknowledgeablePubsubMessage originalMessage = message.getHeaders().get(
                    GcpPubSubHeaders.ORIGINAL_MESSAGE, BasicAcknowledgeablePubsubMessage.class
            );

            if(originalMessage != null) {
                try {
                    BaseEmail mailModel = objectMapper.readValue(jsonString , BaseEmail.class);
                    emailService.sendMail(mailModel);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
                originalMessage.ack();
            }
        };
    }
}


