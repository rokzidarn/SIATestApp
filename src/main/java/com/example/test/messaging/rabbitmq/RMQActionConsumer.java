package com.example.test.messaging.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RMQActionConsumer implements ActionMessagingConsumer{

    private RabbitTemplate rabbit;
    private MessageConverter converter;

    @Autowired
    public RMQActionConsumer(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
        this.converter = rabbit.getMessageConverter();
    }

    public Action consumeAddItemAction() {  // pull method, actively check if something is in queue, if not wait 30s
        Message message = rabbit.receive("spring.action", 30000);  // default, queue == routing key
        return message != null ? (Action) converter.fromMessage(message) : null;  // must be ready for null return
    }
    // push method, listener passively waits until something comes into queue, then start consuming
}