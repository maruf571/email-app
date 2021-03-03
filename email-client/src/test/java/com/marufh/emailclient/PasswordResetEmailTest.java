package com.marufh.emailclient;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.marufh.emailclient.model.BaseEmail;
import com.marufh.emailclient.model.PasswordResetEmail;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordResetEmailTest {

    @Test
    public void testConversion() throws JsonProcessingException {

        PasswordResetEmail passwordResetMail = PasswordResetEmail.passwordResetBuilder()
                .subject("Password Reset")
                .resetPasswordLink("reset-password-link")
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String passwordResetStr =  objectMapper.writeValueAsString(passwordResetMail);
        PasswordResetEmail mailModel =  (PasswordResetEmail) objectMapper.readValue(passwordResetStr, BaseEmail.class);

        Assertions.assertEquals("Password Reset", mailModel.getSubject());
        Assertions.assertEquals("reset-password-link", mailModel.getResetPasswordLink());

    }
}
