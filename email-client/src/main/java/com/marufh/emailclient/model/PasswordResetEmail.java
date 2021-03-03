package com.marufh.emailclient.model;

import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@JsonTypeName("password-reset")
public class PasswordResetEmail extends BaseEmail {

    private String resetPasswordLink;
    private String operatingSystem;
    private String browserName;

    @Builder(builderMethodName = "passwordResetBuilder")
    public PasswordResetEmail(String template,
                       String to,
                       String subject,
                       String fromName,
                       String fromEmail,
                       String productName,
                       String companyAddress,
                       String senderName,
                       String recipientName,
                       String supportEmail,
                       String resetPasswordLink,
                       String operatingSystem,
                       String browserName) {
        super(template, to, subject, fromName, fromEmail, productName, companyAddress, senderName, recipientName, supportEmail);
        this.resetPasswordLink = resetPasswordLink;
        this.operatingSystem = operatingSystem;
        this.browserName = browserName;

    }
}
