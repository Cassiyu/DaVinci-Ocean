package br.com.fiap.oceanapi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.oceanapi.model.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findByDataBetweenAndLocalizacao(LocalDateTime dataInicio, LocalDateTime dataFim, String localizacao);
}
