package com.marufh.emailservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marufh.emailclient.model.BaseEmail;
import com.marufh.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.cloud.aws.messaging.listener.SqsMessageDeletionPolicy;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Slf4j
@EnableSqs
@Configuration
@RequiredArgsConstructor
public class AwsSqsConfig {

    private final ObjectMapper objectMapper;
    private final EmailService emailService;
    private final AwsProperties awsProperties;

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync() {
        return AmazonSQSAsyncClientBuilder
                .standard()
                .withRegion(awsProperties.getRegionName())
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(awsProperties.getAccessKey(),
                                awsProperties.getSecretKey())
                        )
                )
                .build();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }

    @SqsListener(value = "emailQueue", deletionPolicy = SqsMessageDeletionPolicy.ON_SUCCESS)
    public void receiveMessage(String message) {
        try {
            BaseEmail mailModel = objectMapper.readValue(message , BaseEmail.class);
            emailService.sendMail(mailModel);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
