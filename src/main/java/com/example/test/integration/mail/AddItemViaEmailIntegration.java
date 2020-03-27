package com.example.test.integration.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mail.dsl.Mail;

@Configuration
public class AddItemViaEmailIntegration {
    // integration flow: imap inbound channel (get email) -> transformer (extract email info, item data) -> ...
    // ... -> handler (add item to DB)

    @Bean
    public IntegrationFlow addItemEmailFlow(EmailProperties emailProps, EmailToItemTransformer emailToItemTransformer,
                                              ItemSubmitMessageHandler itemSubmitHandler) {

        return null;  // TODO: test email
        /*
        return IntegrationFlows.from(Mail.imapInboundAdapter(emailProps.getImapUrl()),
                        e -> e.poller(Pollers.fixedDelay(emailProps.getPollRate())))
                                        .transform(emailToItemTransformer).handle(itemSubmitHandler).get();
         */
    }

}