package com.marufh.emailclient;

import com.marufh.emailclient.model.BaseEmail;
import com.marufh.emailclient.model.EmailTemplateType;
import com.marufh.emailclient.model.WelcomeEmail;
import com.marufh.emailclient.restclient.EmailClient;

public class Main {

    public static void main(String[] args) {
        EmailClient emailClient = new EmailClient();
        BaseEmail baseEmail = WelcomeEmail.welcomeBuilder()
                .to("awesome@gmail.com")
                .subject("Test email client")
                .fromName("Maruf")
                .fromEmail("no-reply@awesome.com")
                .template(EmailTemplateType.WELCOME.getValue())
                .supportEmail("support@awesome.com")
                .chatLink("chat")
                .senderName("Maruf")
                .companyAddress("xyz")
                .productName("Test")
                .recipientName("Test")
                .activationLink("xyz")
                .build();
        emailClient.sendEmailToAwsSqs(baseEmail);
    }
}
