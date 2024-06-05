package br.com.fiap.oceanapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.oceanapi.model.Relatorio;
import br.com.fiap.oceanapi.service.RelatorioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDateTime;

@RestController
@RequestMapping("relatorio")
@Tag(name = "relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    @Operation(summary = "Gerar Relatório", description = "Gera um relatório de temperatura para um determinado período e localização.")
    public Relatorio gerarRelatorio(
            @RequestParam LocalDateTime dataInicio,
            @RequestParam LocalDateTime dataFim,
            @RequestParam String localizacao) {
        return relatorioService.gerarRelatorio(dataInicio, dataFim, localizacao);
    }
}
