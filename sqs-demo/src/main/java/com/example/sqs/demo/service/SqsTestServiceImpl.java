package com.example.sqs.demo.service;

import com.example.sqs.demo.infraestructure.QueueInfraestructure;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SqsTestServiceImpl implements SqsTestService {
    private final QueueInfraestructure queueInfraestructure;

    @Value("${queue.sqsTest}")
    private String urlQueue;

    @Override
    public Object testingSqsService(String message) {
        return new Object() {
            public final boolean success = true;
            public final String content = message;
            public final String uuidQueue = queueInfraestructure.sendMessage(urlQueue, message);
        };
    }
}
