package br.com.fiap.oceanapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "FIAPDV_RELATORIO")
public class Relatorio {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long relatorio_id;
    
    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime dataInicio;
    
    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime dataFim;
    
    private String localizacao;
    
    private Double temperaturaMedia;
    
    private Double temperaturaMaxima;
    
    private Double temperaturaMinima;
}
