package com.pod3.demo.models;

import lombok.Data;

@Data
public class LogMessage {
    public static enum Type {
        INFO,
        DEBUG,
        WARNING,
        ERROR
    };
    String service;
    String className;
    String message;
    Type type;

    public LogMessage() {
    }

    public LogMessage(String service, String className, String message, Type type) {
        this.service = service;
        this.className = className;
        this.message = message;
        this.type = type;
    }

    public String toString() {
        return String.format("service: %s, class: %s, type: %s, message: %s", service, className, type.name(), message);
    }
}
