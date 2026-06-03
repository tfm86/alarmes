package com.tiago.alarmes.consumer;

import com.tiago.alarmes.model.Alarme;
import com.tiago.alarmes.service.AlarmeService;
import org.springframework.stereotype.Component;

@Component
public class AlarmeConsumer {

    private final AlarmeService service;

    public AlarmeConsumer(AlarmeService service) {
        this.service = service;
    }

    public void receberMensagem(Alarme alarme) throws IllegalAccessException {
        service.salvar(alarme);
        System.out.println("Alarme recebido da fila:" + alarme);
    }

}
