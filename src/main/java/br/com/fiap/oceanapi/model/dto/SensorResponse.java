package br.com.fiap.oceanapi.model.dto;

import br.com.fiap.oceanapi.model.Sensor;
import java.time.LocalDateTime;

public record SensorResponse(
        Long sensor_id,
        LocalDateTime data,
        Double temperatura,
        String localizacao) {

    public static SensorResponse fromSensor(Sensor sensor) {
        return new SensorResponse(sensor.getSensor_id(), sensor.getData(), sensor.getTemperatura(), sensor.getLocalizacao());
    }

}
