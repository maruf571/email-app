package com.marufh.emailservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marufh.emailclient.model.BaseEmail;
import com.marufh.emailservice.config.AwsProperties;
import com.marufh.emailservice.config.GcloudPubSub;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Slf4j
@Controller
@Profile("dev")
@RequiredArgsConstructor
public class EmailTemplateTestController {

    private final ObjectMapper objectMapper;
    private final GcloudPubSub gcloudPubSub;
    private final AwsProperties awsProperties;
    private final QueueMessagingTemplate queueMessagingTemplate;

    @GetMapping
    public String index() {
        return "index";
    }

    @GetMapping("template/{templateName}")
    public String testTemplate(@PathVariable String templateName,  ModelMap modelMap) throws IOException {
        BaseEmail baseEmail = objectMapper.readValue( ResourceUtils.getFile("classpath:test-data/"+templateName+".json"), BaseEmail.class);
        modelMap.put("mailModel", baseEmail);
        return templateName;
    }

    @PostMapping("template/{templateName}/send/gcloud-pubsub")
    public String sendToGcloudPubsub(@PathVariable String templateName) throws IOException {
        BaseEmail baseEmail = objectMapper.readValue( ResourceUtils.getFile("classpath:test-data/"+templateName+".json"), BaseEmail.class);
        baseEmail.setSubject("Test email through gcloud pubsub");
        String str = objectMapper.writeValueAsString(baseEmail);
        gcloudPubSub.send(str);
        return "redirect:/";
    }

    @PostMapping("template/{templateName}/send/aws-sqs")
    public String sendAwsSqs(@PathVariable String templateName) throws IOException {
        BaseEmail baseEmail = objectMapper.readValue( ResourceUtils.getFile("classpath:test-data/"+templateName+".json"), BaseEmail.class);
        baseEmail.setSubject("Test email through aws sqs");
        queueMessagingTemplate.convertAndSend(awsProperties.getQueueName(), baseEmail);
        return "redirect:/";
    }
}
