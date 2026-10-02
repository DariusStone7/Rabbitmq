package com.rabbitmq.publisher.config;

import com.rabbitmq.publisher.Receiver.Tuto2Receiver;
import com.rabbitmq.publisher.sender.Tuto2Sender;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tuto2","work-queues"})
@Configuration
public class Tuto2Config {

    @Bean
    public Queue hello(){
        return QueueBuilder.durable("task_queue").quorum().build();
    }

    @Profile("receiver")
    private static class ReceiverConfig{

        @Bean
        public Tuto2Receiver receiver1(){
            return new Tuto2Receiver(1);
        }

        @Bean
        public Tuto2Receiver receiver2(){
            return new Tuto2Receiver(2);
        }
    }

    @Profile("sender")
    @Bean
    public Tuto2Sender sender(){
        return new Tuto2Sender();
    }
}
