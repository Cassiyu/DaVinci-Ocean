package br.com.fiap.oceanapi.config;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.fiap.oceanapi.model.Relatorio;
import br.com.fiap.oceanapi.model.Sensor;
import br.com.fiap.oceanapi.repository.RelatorioRepository;
import br.com.fiap.oceanapi.repository.SensorRepository;

@Configuration
@Profile("dev")
public class DatabaseSeeder implements CommandLineRunner {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private RelatorioRepository relatorioRepository;

    @Override
    public void run(String... args) throws Exception {

        sensorRepository.saveAll(
                List.of(
                        Sensor.builder()
                                .data(LocalDateTime.now().minusDays(1))
                                .temperatura(25.5)
                                .localizacao("Recife 1")
                                .build(),
                        Sensor.builder()
                                .data(LocalDateTime.now().minusDays(2))
                                .temperatura(24.8)
                                .localizacao("Recife 2")
                                .build(),
                        Sensor.builder()
                                .data(LocalDateTime.now().minusDays(3))
                                .temperatura(26.3)
                                .localizacao("Recife 3")
                                .build(),
                        Sensor.builder()
                                .data(LocalDateTime.now().minusDays(4))
                                .temperatura(23.7)
                                .localizacao("Recife 4")
                                .build()
                )
        );

        relatorioRepository.saveAll(
                List.of(
                        Relatorio.builder()
                                .data_inicio(LocalDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0))
                                .data_fim(LocalDateTime.now().withHour(23).withMinute(59).withSecond(59))
                                .localizacao("Recife 1")
                                .temperatura_media(25.2)
                                .temperatura_maxima(26.0)
                                .temperatura_minima(24.5)
                                .build(),
                        Relatorio.builder()
                                .data_inicio(LocalDateTime.now().minusDays(2).withHour(0).withMinute(0).withSecond(0))
                                .data_fim(LocalDateTime.now().minusDays(1).withHour(23).withMinute(59).withSecond(59))
                                .localizacao("Recife 2")
                                .temperatura_media(24.7)
                                .temperatura_maxima(25.5)
                                .temperatura_minima(24.0)
                                .build(),
                        Relatorio.builder()
                                .data_inicio(LocalDateTime.now().minusDays(3).withHour(0).withMinute(0).withSecond(0))
                                .data_fim(LocalDateTime.now().minusDays(2).withHour(23).withMinute(59).withSecond(59))
                                .localizacao("Recife 3")
                                .temperatura_media(26.0)
                                .temperatura_maxima(26.8)
                                .temperatura_minima(25.5)
                                .build(),
                        Relatorio.builder()
                                .data_inicio(LocalDateTime.now().minusDays(4).withHour(0).withMinute(0).withSecond(0))
                                .data_fim(LocalDateTime.now().minusDays(3).withHour(23).withMinute(59).withSecond(59))
                                .localizacao("Recife 4")
                                .temperatura_media(23.5)
                                .temperatura_maxima(24.0)
                                .temperatura_minima(23.0)
                                .build()
                )
        );
    }
}
