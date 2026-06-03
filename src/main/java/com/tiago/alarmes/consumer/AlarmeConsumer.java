package com.tiago.alarmes.consumer;

import com.tiago.alarmes.model.Alarme;
import com.tiago.alarmes.service.AlarmeService;
import com.tiago.alarmes.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class AlarmeConsumer {

    private final AlarmeService service;

    public AlarmeConsumer(AlarmeService service) {
        this.service = service;
    }

    @RabbitListener(queues = RabbitMQConfig.FILA_ALARMES)
    public void receberMensagem(Alarme alarme) throws IllegalAccessException {
        System.out.println("Alarme recebido da fila: " + alarme);
        service.salvar(alarme);
    }
}
