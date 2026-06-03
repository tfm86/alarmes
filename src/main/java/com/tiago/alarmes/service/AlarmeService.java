package com.tiago.alarmes.service;

import com.tiago.alarmes.model.Alarme;
import com.tiago.alarmes.repository.AlarmeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlarmeService {

    private final AlarmeRepository repository;

    public AlarmeService(AlarmeRepository repository) {
        this.repository = repository;
    }

    public Alarme salvar(Alarme alarme) throws IllegalAccessException {
        //Se timestamp não vier
        if (alarme.getTimestamp() == null) {
            alarme.setTimestamp(LocalDateTime.now());
        }

        //validar simples
        if (alarme.getMensagem() == null || alarme.getMensagem().isBlank()) {
            throw new IllegalAccessException("Mensagem do alarme não pode ser vazio");
        }

        return repository.save(alarme);
    }

    public List<Alarme> listarTodos() {
        return repository.findAll();
    }

    public List<Alarme> buscarPorSeveridade(String severidade) {
        return repository.findBySeveridade(severidade);
    }

    public List<Alarme> buscarPorOrigem(String origem) {
        return repository.findByOrigem(origem);
    }

}
