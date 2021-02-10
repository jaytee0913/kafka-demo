package com.pod3.demo.models;

import lombok.Data;

@Data
public class MailMessage {
    private String toEmail;
    private String subject;
    private String message;

    public MailMessage(String email, String service, String message) {
        this.toEmail = email;
        this.subject = String.format("Error Message from %s", service);
        this.message = message;
    }

    public void printMessage() {
        System.out.println(String.format("toEmail: %s", toEmail));
        System.out.println(String.format("subject: %s", subject));
        System.out.println(String.format("message: %s", message));
    }
}
