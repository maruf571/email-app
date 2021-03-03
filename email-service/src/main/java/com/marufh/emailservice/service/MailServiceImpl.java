package com.marufh.emailservice.service;


import com.marufh.emailclient.model.BaseEmail;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MailServiceImpl implements EmailService {

    private static final String TEMPLATE_EXTENSION = ".ftl";
    private final Mailer mailer;
    private final Configuration freemarkerConfig;

    @Override
    public void sendMail(BaseEmail mailModel) {
        log.info("sending email to " + mailModel.getTo());
        String html = null;
        try {
            Template template = freemarkerConfig.getTemplate( mailModel.getTemplate() + TEMPLATE_EXTENSION);
            html = FreeMarkerTemplateUtils.processTemplateIntoString(
                    template,
                    new ModelMap("mailModel", mailModel)
            );
        } catch (IOException | TemplateException e) {
            e.printStackTrace();
        }

        mailer.sendMail(
                EmailBuilder.startingBlank()
                        .from(mailModel.getFromName(), mailModel.getFromEmail())
                        .to(mailModel.getTo())
                        .withSubject(mailModel.getSubject())
                        .withHTMLText(html)
                        .buildEmail()
        );
    }
}
