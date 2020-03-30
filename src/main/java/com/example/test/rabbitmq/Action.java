package com.example.test.rabbitmq;

import lombok.Data;

import java.io.Serializable;

@Data
public class Action implements Serializable {

    private String who;
    private String where;
    private String when;
    private String what;
}