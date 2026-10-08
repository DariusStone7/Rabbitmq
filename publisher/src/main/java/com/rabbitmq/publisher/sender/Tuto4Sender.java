package com.rabbitmq.publisher.sender;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

public class Tuto4Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange direct;

    AtomicInteger index = new AtomicInteger();

    AtomicInteger count = new AtomicInteger();

    private final String[] keys = {"orange", "black", "green"};

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(){
        StringBuilder builder = new StringBuilder("Hello to ");
        if(this.index.incrementAndGet() == 3){
            this.index.set(0);
        }
        String key = keys[this.index.get()];
        builder.append(key).append(' ');
        builder.append(this.count.incrementAndGet());
        String message = builder.toString();
        template.convertAndSend(direct.getName(), key, message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
