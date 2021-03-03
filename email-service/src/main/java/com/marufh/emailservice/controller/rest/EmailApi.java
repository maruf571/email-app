package com.marufh.emailservice.controller.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marufh.emailclient.model.BaseEmail;
import com.marufh.emailservice.config.AwsProperties;
import com.marufh.emailservice.config.SmtpProperties;
import com.marufh.emailservice.config.GcloudPubSub;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(EmailApi.URL)
public class EmailApi {

    public static final String URL = "/api/send/mail";
    private final GcloudPubSub gcloudPubSub;
    private final ObjectMapper objectMapper;
    private final QueueMessagingTemplate queueMessagingTemplate;
    private final AwsProperties awsProperties;


    @PostMapping("/gcloud-pubsub")
    public void emailSendRequestReceiver(@RequestBody @Valid BaseEmail emailData) throws JsonProcessingException {
        gcloudPubSub.send( objectMapper.writeValueAsString(emailData));
    }

    @PostMapping("/aws-sqs")
    public void send(@RequestBody @Valid BaseEmail emailData) throws JsonProcessingException {
        queueMessagingTemplate.convertAndSend(awsProperties.getQueueName(), objectMapper.writeValueAsString(emailData));
    }
}
