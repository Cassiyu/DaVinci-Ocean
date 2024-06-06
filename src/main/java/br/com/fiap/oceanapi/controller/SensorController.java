package br.com.fiap.oceanapi.controller;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.NO_CONTENT;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.fiap.oceanapi.model.Sensor;
import br.com.fiap.oceanapi.repository.SensorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("sensor")
@Slf4j
@CacheConfig(cacheNames = "sensor")
@Tag(name = "sensor")
public class SensorController {

    @Autowired
    private SensorRepository sensorRepository;

    @GetMapping
    @Cacheable
    @Operation(summary = "Listar Sensores", description = "Retorna um array com todos os sensores.")
    public List<Sensor> listAllSensors() {
        log.info("Listando todos os sensores");
        return sensorRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Sensor criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique o corpo da requisição")
    })
    @CacheEvict(allEntries = true)
    public Sensor createSensor(@RequestBody Sensor sensor) {
        log.info("Cadastrando sensor {}", sensor);
        return sensorRepository.save(sensor);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar Sensor por ID", description = "Retorna um sensor pelo seu ID.")
    public ResponseEntity<Sensor> getSensorById(@PathVariable Long id) {
        log.info("Buscando sensor com id {} ", id);
        return sensorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @CacheEvict(allEntries = true)
    @Operation(summary = "Atualizar Sensor", description = "Atualiza um sensor existente pelo seu ID.")
    public ResponseEntity<Sensor> updateSensor(@PathVariable Long id, @RequestBody Sensor sensor) {
        log.info("Atualizando sensor com id {} para {}", id, sensor);
        if (!sensorRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Sensor não encontrado");
        }
        sensor.setSensor_id(id);
        Sensor updatedSensor = sensorRepository.save(sensor);
        return ResponseEntity.ok(updatedSensor);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    @CacheEvict(allEntries = true)
    @Operation(summary = "Deletar Sensor", description = "Deleta um sensor existente pelo seu ID.")
    public void deleteSensor(@PathVariable Long id) {
        log.info("Deletando sensor com id {}", id);
        if (!sensorRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Sensor não encontrado");
        }
        sensorRepository.deleteById(id);
    }

    @GetMapping("/mock")
    public List<Sensor> getMockSensors() {
        List<Sensor> sensors = new ArrayList<>();
        Random random = new Random();
        
        for (int i = 0; i < 9; i++) { 
            String localizacao = "Recife " + (i + 1);
            
            Sensor sensor = Sensor.builder()
                    .data(LocalDateTime.now())
                    .temperatura(15 + random.nextDouble() * 10)
                    .localizacao(localizacao)
                    .build();
            sensors.add(sensor);
        }
        return sensors;
    }
    
}
