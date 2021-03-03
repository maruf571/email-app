package com.marufh.emailclient;

import com.marufh.emailclient.model.EmailTemplateType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class EmailTemplateTypeTest {

    @Test
    public void testGetValue() {
        Assertions.assertEquals("welcome", EmailTemplateType.WELCOME.getValue());
        Assertions.assertEquals("WELCOME", EmailTemplateType.WELCOME.name());
    }
}
