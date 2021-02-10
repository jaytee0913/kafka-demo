package com.pod3.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pod3.demo.models.LogMessage;
import com.pod3.demo.models.MailMessage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ErrorListenerServiceTest {
    @Mock
    MailService mockMailService;

    @InjectMocks
    ErrorListenerService errorListenerService;

    @BeforeEach
    public void setupBeforeEach() {
        errorListenerService = new ErrorListenerService();
    }

    @Test
    public void consume_shouldCallMailService_ifNoError() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        LogMessage mockLogMessage = new LogMessage("mockService", "mockClass", "mockMessage", LogMessage.Type.ERROR);
        String mockJsonMessage = objectMapper.writeValueAsString(mockLogMessage);
//        ObjectMapper mockObjectMapper = mock(ObjectMapper.class);
//        when(mockObjectMapper.readValue(any(String.class), LogMessage.class)).thenReturn(mockLogMessage);
        mockMailService = mock(MailService.class);
        doNothing().when(mockMailService).sendErrorNotification(any());
        errorListenerService.consume(mockJsonMessage);
//        verify(mockObjectMapper).readValue(any(String.class), LogMessage.class);
        verify(mockMailService).sendErrorNotification(any());
    }
}
