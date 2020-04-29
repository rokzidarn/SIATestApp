package com.example.test.controller;

import com.example.test.messaging.rabbitmq.Action;
import com.example.test.messaging.rabbitmq.RMQActionConsumer;
import com.example.test.messaging.rabbitmq.RMQActionProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.test.integration.file.FileWriterGateway;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ActionController {

    private final RMQActionProducer rmqProducer;  // http://localhost:15672/mgmt
    private final RMQActionConsumer rmqConsumer;  // credentials: guest/guest

    @Autowired
    FileWriterGateway gateway;

    @GetMapping("/action/produce")
    public String produceMessageAction() {
        Action example = new Action();
        example.setWho("Rok");
        example.setWhat("Sleeping");
        example.setWhen("Now");
        example.setWhere("Couch");

        rmqProducer.produceAddItemAction(example);
        log.info("Message produced!");
        return "action";
    }

    @GetMapping("/action/consume")
    public String consumeMessageAction() {
        Action example = rmqConsumer.consumeAddItemAction();
        if (example != null) {
            log.info("Message consumed:  " + example.getWho() + " is " + example.getWhen() + " " + example.getWhat() + " on the " + example.getWhere());
            return "action";
        }
        return "error";
    }

    @GetMapping("/action/file")
    public String writeFile() {
        log.info("Writing to file!");
        gateway.writeToFile("action.txt", new Date().toString() + ": Action!");
        return "action";
    }
}
