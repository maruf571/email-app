package com.marufh.emailclient.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;

import javax.validation.constraints.NotBlank;


@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "name")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PasswordResetEmail.class, name = "password-reset"),
        @JsonSubTypes.Type(value = WelcomeEmail.class, name = "welcome")
})
public class BaseEmail {


    @NotBlank(message = "Mail template is blank")
    private String template;

    @NotBlank(message = "Mail receiver is blank")
    private String to;

    @NotBlank(message = "Mail subject is blank")
    private String subject;

    @NotBlank(message = "Mail sender name is blank")
    private String fromName;

    @NotBlank(message = "Mail sender is blank")
    private String fromEmail;

    @NotBlank(message = "Product name is blank")
    private String productName;

    @NotBlank(message = "Company address is blank")
    private String companyAddress;

    @NotBlank(message = "Support email is blank")
    private String supportEmail;

    private String senderName;

    private String recipientName;

}
