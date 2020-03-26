package com.example.test.integration.file;

import java.io.File;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.file.FileWritingMessageHandler;

import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.integration.transformer.GenericTransformer;

@Configuration
public class FileWriterIntegrationFlow {

    @Bean
    @Transformer(inputChannel="textInChannel", outputChannel="fileWriterChannel")  // from where to somewhere
    public GenericTransformer<String, String> upperCaseTransformer() {  // transform text to uppercase
        return text -> text.toUpperCase();
    }

    /* @Bean
    @Filter(inputChannel="numberFilterChannel", outputChannel="evenNumberChannel")
    public boolean evenNumberFilter(Integer number) {
        return number % 2 == 0;
    } */

    @Bean
    @ServiceActivator(inputChannel="fileWriterChannel")  // receives text from transformer, writes to file
    public FileWritingMessageHandler fileWriter() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File("tmp"));
        handler.setExpectReply(false);
        handler.setFileExistsMode(FileExistsMode.APPEND);
        handler.setAppendNewLine(true);  // appends text to file and adds newline
        return handler;
    }
}