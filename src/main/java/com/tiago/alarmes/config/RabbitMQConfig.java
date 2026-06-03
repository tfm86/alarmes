package com.tiago.alarmes.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

public class RabbitMQConfig {

    public static final String FILA_ALARMES = "alarmes.fila";

    @Bean
    public Queue filaAlarmes() {
        return new Queue(FILA_ALARMES, true);
    }


}
