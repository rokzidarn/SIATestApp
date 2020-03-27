package com.example.test.integration.mail;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@ConfigurationProperties(prefix="test.email")
@Component
public class EmailProperties {

    private String username;  // configuration in application.properties
    private String password;
    private String host;
    private String mailbox;
    private long pollRate = 10000;

    public String getImapUrl() {
        return String.format("imaps://%s:%s@%s/%s", this.username, this.password, this.host, this.mailbox);
    }

}