package com.example.test.controller;

import com.example.test.messaging.kafka.EventProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/event")
public class EventController {

    // .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties (fix properties file)
    // .\bin\windows\kafka-server-start.bat .\config\server.properties (create C:/tmp/kafka-logs directory and edit permissions)
    // GET -> http://localhost:8080/event/publish

    private final EventProducer eventProducer;

    @Autowired
    public EventController(EventProducer eventProducer) {
        this.eventProducer = eventProducer;
    }

    @GetMapping(value = "/publish")
    public void sendMessageToKafkaTopic() {
        this.eventProducer.sendMessage("Event!");
    }
}