package com.example.sqs.demo.listener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.annotation.JmsListeners;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@AllArgsConstructor
public class SqsListener {

    @JmsListeners(value = {
            @JmsListener(destination = "SqsTest")
    })
    public void receive(String message) {
        log.info("Received {}", message);

        try {
            JsonNode jsonNode = new ObjectMapper().readTree(message);
        } catch (Exception e) {
            log.error("Error in receive portfolio data", e);
        }
    }
}
