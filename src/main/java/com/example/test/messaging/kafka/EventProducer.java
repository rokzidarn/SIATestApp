package com.example.test.messaging.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {

    // Kafka is run as a cluster in one or more servers
    // topics can have zero, one, or multiple consumers, who will subscribe to the data written to that topic
    // Kafka cluster uses a partitioned log for each topic,
        // partition maintains the order in which data was inserted and once the record is published to the topic
        // maintains a flag called 'offsets,' which uniquely identifies each record within the partition

    private static final Logger logger = LoggerFactory.getLogger(EventProducer.class);
    private static final String TOPIC = "events";

    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    public void sendMessage(String message) {
        logger.info(String.format("$$ -> Producing message --> %s", message));
        this.kafkaTemplate.send(TOPIC, message);
    }
}
