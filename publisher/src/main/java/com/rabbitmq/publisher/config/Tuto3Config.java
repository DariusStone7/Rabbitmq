package com.rabbitmq.publisher.config;


import com.rabbitmq.publisher.Receiver.Tuto3Receiver;
import com.rabbitmq.publisher.sender.Tuto3Sender;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile({"tuto3", "pub-sub", "publish-subscribe"})
@Configuration
public class Tuto3Config {

    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("tuto3.fanout");
    }

    @Profile("receiver")
    private static class ReceiverConfig {

        @Bean
        public Queue autoDeleteQueue1() {
            return new AnonymousQueue();
        }

        @Bean
        public Queue autoDeleteQueue2() {
            return  new AnonymousQueue();
        }

        @Bean
        public Binding binding1(FanoutExchange fanout, Queue autoDeleteQueue1){
            return BindingBuilder.bind(autoDeleteQueue1).to(fanout);
        }

        @Bean
        public Binding binding2(FanoutExchange fanout, Queue autoDeleteQueue2){
            return BindingBuilder.bind(autoDeleteQueue2).to(fanout);
        }

        @Bean
        public Tuto3Receiver receiver(){
            return new Tuto3Receiver();
        }
    }

    @Profile("sender")
    @Bean
    public Tuto3Sender sender(){
        return new Tuto3Sender();
    }
}
