package br.com.fiap.oceanapi.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.OptionalDouble;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.oceanapi.model.Relatorio;
import br.com.fiap.oceanapi.model.Sensor;
import br.com.fiap.oceanapi.repository.SensorRepository;

@Service
public class RelatorioService {

    @Autowired
    private SensorRepository sensorRepository;

    public Relatorio gerarRelatorio(LocalDateTime dataInicio, LocalDateTime dataFim, String localizacao) {
        List<Sensor> sensores = sensorRepository.findByDataLocalizacao(dataInicio, dataFim, localizacao);

        OptionalDouble temperaturaMedia = sensores.stream().mapToDouble(Sensor::getTemperatura).average();
        OptionalDouble temperaturaMaxima = sensores.stream().mapToDouble(Sensor::getTemperatura).max();
        OptionalDouble temperaturaMinima = sensores.stream().mapToDouble(Sensor::getTemperatura).min();

        return Relatorio.builder()
                .dataInicio(dataInicio)
                .dataFim(dataFim)
                .localizacao(localizacao)
                .temperaturaMedia(temperaturaMedia.orElse(Double.NaN))
                .temperaturaMaxima(temperaturaMaxima.orElse(Double.NaN))
                .temperaturaMinima(temperaturaMinima.orElse(Double.NaN))
                .build();
    }
}
