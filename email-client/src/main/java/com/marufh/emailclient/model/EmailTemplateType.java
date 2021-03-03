package com.marufh.emailclient.model;

public enum EmailTemplateType {

    WELCOME("welcome"),
    PASSWORD_RESET("password-reset");

    private String value;
    EmailTemplateType(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
