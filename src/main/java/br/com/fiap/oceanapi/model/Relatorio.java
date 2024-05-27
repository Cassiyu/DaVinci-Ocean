package br.com.fiap.oceanapi.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Relatorio {
    
    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime dataInicio;
    
    @JsonFormat(pattern = "dd-MM-yyyy'T'HH:mm:ss")
    private LocalDateTime dataFim;
    
    private String localizacao;
    
    private Double temperaturaMedia;
    
    private Double temperaturaMaxima;
    
    private Double temperaturaMinima;
}
