package com.rabbitmq.publisher.sender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.concurrent.atomic.AtomicInteger;

public class Tuto2Sender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private Queue queue;

    AtomicInteger dots = new AtomicInteger(0);
    AtomicInteger count = new AtomicInteger(0);

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send(){
        StringBuilder stringBuilder = new StringBuilder("Hello");
        if(dots.incrementAndGet() == 4){
            dots.set(1);
        }
        for(int i=0; i<dots.get(); i++){
            stringBuilder.append('.');
        }
        stringBuilder.append(count.incrementAndGet());
        String message = stringBuilder.toString();
        rabbitTemplate.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
    }
}
