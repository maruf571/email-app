package com.marufh.emailclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marufh.emailclient.model.BaseEmail;
import com.marufh.emailclient.model.PasswordResetEmail;
import com.marufh.emailclient.model.WelcomeEmail;

public class TestModel {

    public static void main(String[] args) throws JsonProcessingException {

        WelcomeEmail welcomeMail = WelcomeEmail.welcomeBuilder()
                .chatLink("chatlink")
                .subject("Welcome")
                .build();
        welcomeMail.setActivationLink("active link");
        welcomeMail.setChatLink("chat-link");

        ObjectMapper objectMapper = new ObjectMapper();
        String welcomeStr =  objectMapper.writeValueAsString(welcomeMail);


        PasswordResetEmail passwordResetMail = PasswordResetEmail.passwordResetBuilder()
                .fromEmail("no-reply@ehrbd.com")
                .subject("Subject")
                .fromName("EHRBD")
                .companyAddress("Berlin, Germany")
                .productName("EHRBD")
                .to("to@gmail.com")
                .senderName("")
                .build();
        passwordResetMail.setResetPasswordLink("password reset link");
        String passwordResetStr =  objectMapper.writeValueAsString(passwordResetMail);



        BaseEmail welcomeModel =  objectMapper.readValue(welcomeStr, BaseEmail.class);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(welcomeModel));


        BaseEmail passwordResetModel  =  objectMapper.readValue(passwordResetStr, BaseEmail.class);
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(passwordResetModel));
    }
}
