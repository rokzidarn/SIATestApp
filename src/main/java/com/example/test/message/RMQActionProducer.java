package com.example.test.message;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RMQActionProducer implements ActionMessagingProducer {

    // brokers: Artemis, RabbitMQ, Kafka
    // publish-subscribe -> topic (multiple consumers, don't know the producer)
    // point-to-point -> queue (one producer to one consumer, FIFO)
    // direct (one consumer, by routing and exchange key matching), fanout (all consumers)
    // default (auto create of default exchange, message sent to those queues where routing key matches queue name)

    private RabbitTemplate rabbit;

    @Autowired
    public RMQActionProducer(RabbitTemplate rabbit) {
        this.rabbit = rabbit;
    }
    public void produceAddItemAction(Action example) {
        MessageConverter converter = rabbit.getMessageConverter();
        MessageProperties props = new MessageProperties();
        Message message = converter.toMessage(example, props);
        rabbit.send("spring.action", message);  // raw message, but before must convert object to message
        // using default (default exchange == ""), will deliver to queue which has the same name as routing key
    }
}