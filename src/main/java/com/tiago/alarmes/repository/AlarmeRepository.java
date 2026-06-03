package com.tiago.alarmes.repository;

import com.tiago.alarmes.model.Alarme;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmeRepository extends JpaRepository<Alarme, Long>{

    List<Alarme> findBySeveridade (String severidade);
    List<Alarme> findByOrigem (String origem);

}


