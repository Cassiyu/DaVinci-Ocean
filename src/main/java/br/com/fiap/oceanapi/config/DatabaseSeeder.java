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
                                .coordenadas("40.7128° N, 74.0060° W")
                                .build(),
                        Sensor.builder()
                                .data(LocalDateTime.now().minusDays(2))
                                .temperatura(24.8)
                                .coordenadas("34.0522° N, 118.2437° W")
                                .build(),
                        Sensor.builder()
                                .data(LocalDateTime.now().minusDays(3))
                                .temperatura(26.3)
                                .coordenadas("41.8781° N, 87.6298° W")
                                .build(),
                        Sensor.builder()
                                .data(LocalDateTime.now().minusDays(4))
                                .temperatura(23.7)
                                .coordenadas("51.5074° N, 0.1278° W")
                                .build()
                )
        );

        relatorioRepository.saveAll(
                List.of(
                        Relatorio.builder()
                                .dataInicio(LocalDateTime.now().minusDays(1).withHour(0).withMinute(0).withSecond(0))
                                .dataFim(LocalDateTime.now().withHour(23).withMinute(59).withSecond(59))
                                .localizacao("40.7128° N, 74.0060° W")
                                .temperaturaMedia(25.2)
                                .temperaturaMaxima(26.0)
                                .temperaturaMinima(24.5)
                                .build(),
                        Relatorio.builder()
                                .dataInicio(LocalDateTime.now().minusDays(2).withHour(0).withMinute(0).withSecond(0))
                                .dataFim(LocalDateTime.now().minusDays(1).withHour(23).withMinute(59).withSecond(59))
                                .localizacao("34.0522° N, 118.2437° W")
                                .temperaturaMedia(24.7)
                                .temperaturaMaxima(25.5)
                                .temperaturaMinima(24.0)
                                .build(),
                        Relatorio.builder()
                                .dataInicio(LocalDateTime.now().minusDays(3).withHour(0).withMinute(0).withSecond(0))
                                .dataFim(LocalDateTime.now().minusDays(2).withHour(23).withMinute(59).withSecond(59))
                                .localizacao("41.8781° N, 87.6298° W")
                                .temperaturaMedia(26.0)
                                .temperaturaMaxima(26.8)
                                .temperaturaMinima(25.5)
                                .build(),
                        Relatorio.builder()
                                .dataInicio(LocalDateTime.now().minusDays(4).withHour(0).withMinute(0).withSecond(0))
                                .dataFim(LocalDateTime.now().minusDays(3).withHour(23).withMinute(59).withSecond(59))
                                .localizacao("51.5074° N, 0.1278° W")
                                .temperaturaMedia(23.5)
                                .temperaturaMaxima(24.0)
                                .temperaturaMinima(23.0)
                                .build()
                )
        );
    }
}
