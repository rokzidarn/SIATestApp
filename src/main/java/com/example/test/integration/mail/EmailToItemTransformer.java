package com.example.test.integration.mail;

import java.io.IOException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;

import com.example.test.model.Item;
import com.example.test.service.CategoryService;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
public class EmailToItemTransformer extends AbstractMailMessageTransformer<Item> {

    private static final String SUBJECT_KEYWORDS = "ADD ITEM";
    private CategoryService categoryService;

    @Override
    protected AbstractIntegrationMessageBuilder<Item>
    doTransform(Message mailMessage) throws Exception {
        Item item = processPayload(mailMessage);
        return MessageBuilder.withPayload(item);
    }

    private Item processPayload(Message mailMessage) {
        try {
            String subject = mailMessage.getSubject();
            if (subject.toUpperCase().contains(SUBJECT_KEYWORDS)) {  // check if correct subject of email
                String email = ((InternetAddress) mailMessage.getFrom()[0]).getAddress();  // emailer data
                String content = mailMessage.getContent().toString();
                return parseEmailToItem(email, content);
            }
        } catch (MessagingException e) {
        } catch (IOException e) {}
        return null;
    }

    private Item parseEmailToItem(String email, String content) {  // process email payload, create item to insert into DB
        Item item = new Item();
        // example of email payload (one line): item name, item price, item category name
        // Happy Socks Jungle, 11, Socks
        String[] split = content.split(", ");

        item.setName(split[0]);
        item.setPrice(Integer.parseInt(split[1]));
        item.setCategory(categoryService.findCategoryByName(split[0]));
        return item;
    }
}
