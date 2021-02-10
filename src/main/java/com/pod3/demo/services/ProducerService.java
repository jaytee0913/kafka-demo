package com.pod3.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pod3.demo.models.LogMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {
    private static final Logger logger = LogManager.getLogger();

    private static final String TOPIC = "test_topic";

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC, message);
    }

    public void sendMessageBody(LogMessage message) {
        try {
            String messageDTO = objectMapper.writeValueAsString(message);
            kafkaTemplate.send(TOPIC, messageDTO);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException");
        }
    }
}
