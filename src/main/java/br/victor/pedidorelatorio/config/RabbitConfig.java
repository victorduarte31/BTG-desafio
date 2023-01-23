package br.victor.pedidorelatorio.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Value("${queue.consumer}")
    private String queueConsumer;

    @Value("${queue.dlq}")
    private String queueDlq;
    @Value("${exchange}")
    private String exchange;

    @Value("${exchange-dlx}")
    private String exchangeDlx;


    public Queue criarFilaConsumer() {
        return QueueBuilder.durable(queueConsumer)
                .deadLetterExchange(exchangeDlx)
                .build();
    }

    public Queue criarFilaDlq() {
        return QueueBuilder.durable(queueDlq)
                .build();
    }

    public FanoutExchange criarExchange() {
        return ExchangeBuilder.fanoutExchange(exchange).build();
    }
    public FanoutExchange criarExchangeDlx() {
        return ExchangeBuilder.fanoutExchange(exchangeDlx).build();
    }

    @Bean
    public Declarables declarables() {
        criarFilaConsumer();
        criarFilaDlq();
        criarExchange();
        criarExchangeDlx();

        return new Declarables(
                criarFilaConsumer(),
                criarFilaDlq(),
                criarExchange(),
                criarExchangeDlx(),
                BindingBuilder.bind(criarFilaConsumer()).to(criarExchange()),
                BindingBuilder.bind(criarFilaDlq()).to(criarExchangeDlx())
        );
    }

    @Bean
    public RabbitAdmin criarRabbitAdmin(ConnectionFactory factory) {
        return new RabbitAdmin(factory);
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> iniciaRabbitAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory factory, Jackson2JsonMessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }


}
