package com.marufh.emailclient;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marufh.emailclient.model.BaseEmail;
import com.marufh.emailclient.model.WelcomeEmail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WelcomeEmailTest {

    @Test
    public void testConversion() throws JsonProcessingException {
        WelcomeEmail welcomeMail = WelcomeEmail.welcomeBuilder()
                .subject("Welcome")
                .chatLink("chatlink")
                .activationLink("active-link")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String welcomeStr =  objectMapper.writeValueAsString(welcomeMail);
        WelcomeEmail mailModel =  (WelcomeEmail) objectMapper.readValue(welcomeStr, BaseEmail.class);

        Assertions.assertEquals("Welcome", mailModel.getSubject());
        Assertions.assertEquals("chatlink", mailModel.getChatLink());
        Assertions.assertEquals("active-link", mailModel.getActivationLink());

    }
}
