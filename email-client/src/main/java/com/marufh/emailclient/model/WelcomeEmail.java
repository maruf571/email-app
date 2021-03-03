package com.marufh.emailclient.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonTypeName("welcome")
public class WelcomeEmail extends BaseEmail {

    private String activationLink;
    private String chatLink;

    @Builder(builderMethodName = "welcomeBuilder")
    public WelcomeEmail(String template,
                 String to,
                 String subject,
                 String fromName,
                 String fromEmail,
                 String productName,
                 String companyAddress,
                 String senderName,
                 String recipientName,
                 String supportEmail,
                 String activationLink,
                 String chatLink) {
        super(template, to, subject, fromName, fromEmail, productName, companyAddress, senderName, recipientName, supportEmail);
        this.activationLink = activationLink;
        this.chatLink = chatLink;
    }
}
