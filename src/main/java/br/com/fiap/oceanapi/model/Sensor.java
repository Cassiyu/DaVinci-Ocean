package br.com.fiap.oceanapi.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FIAPDV_SENSOR")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sensor_id;

    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime data;

    private Double temperatura;

    private String localizacao;

}
