package com.pod3.demo.services;

import com.pod3.demo.models.MailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    public void sendErrorNotification(MailMessage mailMessage) {
        mailMessage.printMessage();
    }
}
