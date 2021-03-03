package com.marufh.emailservice.service;

import com.marufh.emailclient.model.BaseEmail;

public interface EmailService {
    void sendMail(BaseEmail baseEmail);
}
