package com.example.sqs.demo.infraestructure;

public interface QueueInfraestructure {
    String sendMessage(String urlQueue, Object object);
}
