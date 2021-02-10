package com.pod3.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pod3.demo.models.LogMessage;
import com.pod3.demo.models.MailMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ErrorListenerService {

    private static final String TOPIC = "test_topic";
    private static final String GROUP_ID = "group_id";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final MailService mailService = new MailService();

    @KafkaListener(topics = TOPIC, groupId = GROUP_ID, containerFactory = "kafkaListenerContainerFactory")
    public void consume(String jsonMessage) {
        System.out.println(jsonMessage);
        try {
            LogMessage logMessage = objectMapper.readValue(jsonMessage, LogMessage.class);
            MailMessage mailMessage = new MailMessage("", logMessage.getService(), logMessage.getMessage());
            mailService.sendErrorNotification(mailMessage);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException");
        }
    }
}
