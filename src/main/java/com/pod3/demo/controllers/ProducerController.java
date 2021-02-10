package com.pod3.demo.controllers;

import com.pod3.demo.models.LogMessage;
import com.pod3.demo.services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {
    private final ProducerService producer;

    @Autowired
    public ProducerController(ProducerService producer) {
        this.producer = producer;
    }

    @PostMapping("/log")
    public void messageToTopic(@RequestParam("message") String message) {
        this.producer.sendMessage(message);
    }

    @PostMapping("/publish")
    public void messageObjectToTopic(@RequestBody LogMessage message) { this.producer.sendMessageBody(message); }
}
