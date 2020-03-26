package com.example.test.controller;

import com.example.test.message.Action;
import com.example.test.message.RMQActionConsumer;
import com.example.test.message.RMQActionProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ActionController {

    private final RMQActionProducer rmqProducer;
    private final RMQActionConsumer rmqConsumer;

    @GetMapping("/action/produce")
    public String produceMessageAction() {
        Action example = new Action();
        example.setWho("Rok");
        example.setWhat("Sleeping");
        example.setWhen("Yesterday");
        example.setWhere("Couch");

        rmqProducer.produceAddItemAction(example);
        return "Action produced!";
    }

    @GetMapping("/action/consume")
    public String consumeMessageAction() {
        Action example = rmqConsumer.consumeAddItemAction();
        if (example != null) {
            return "Action consumed!";
        }
        return "No action!";
    }
}
