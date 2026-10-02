package com.rabbitmq.publisher.config;

import com.rabbitmq.publisher.Receiver.Tuto1Receiver;
import com.rabbitmq.publisher.sender.Tuto1Sender;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tuto1","hello-world"})
@Configuration
public class Tuto1Config {

    @Bean
    public Queue hello(){
        return QueueBuilder.durable("hello").quorum().build();
    }

    @Profile("receiver")
    @Bean
    public Tuto1Receiver receiver(){
        return new Tuto1Receiver();
    }

    @Profile("sender")
    @Bean
    public Tuto1Sender sender(){
        return new Tuto1Sender();
    }
}
