package com.tiago.alarmes.controller;

import com.tiago.alarmes.model.Alarme;
import com.tiago.alarmes.producer.AlarmeProducer;
import com.tiago.alarmes.service.AlarmeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alarmes")
public class AlarmeController {

    private final AlarmeService service;
    private final AlarmeProducer producer;

    public AlarmeController(AlarmeService service, AlarmeProducer producer) {
        this.service = service;
        this.producer = producer;
    }

    @PostMapping
    public Alarme criar(@RequestBody Alarme alarme) throws IllegalAccessException {
        return service.salvar(alarme);
    }

    @GetMapping
    public List<Alarme> listar(){
        return service.listarTodos();
    }

    @GetMapping("/severidade/{sev}")
    public List<Alarme> buscarPorSeveridade(@PathVariable String sev) {
        return service.buscarPorSeveridade(sev);
    }

    @GetMapping("/origem/{origem}")
    public List<Alarme> buscarPorOrigem(@PathVariable String origem) {
        return service.buscarPorOrigem(origem);
    }

    @PostMapping("/fila")
    public String enviarParaFila(@RequestBody Alarme alarme) {
        producer.enviarAlarme(alarme);
        return "Alarme enviado para a fila!";
    }



}
