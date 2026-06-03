package com.tiago.alarmes.producer;

import com.tiago.alarmes.config.RabbitMQConfig;
import com.tiago.alarmes.model.Alarme;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class AlarmeProducer {

    private final RabbitTemplate rabbitTemplate;

    public AlarmeProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarAlarme(Alarme alarme) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.FILA_ALARMES, alarme);
        System.out.println("Alarme enviado para fila: " + alarme);
    }

}
