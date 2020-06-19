package com.example.sqs.demo.infraestructure;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class QueueInfraestructureImpl implements QueueInfraestructure {
    private final ObjectMapper mapper = new ObjectMapper();
    private final AmazonSQS amazonSQS;

    @Override
    public String sendMessage(String urlQueue, Object object) {
        SendMessageRequest sendMessageQueue = null;

        try {
            sendMessageQueue = new SendMessageRequest()
                    .withQueueUrl(urlQueue)
                    .withMessageBody(mapper.writeValueAsString(object))
                    .withDelaySeconds(30);
        } catch (JsonProcessingException e) {
            log.error(e);
        }

        SendMessageResult result = this.amazonSQS.sendMessage(sendMessageQueue);

        return result.getMessageId();
    }
}
