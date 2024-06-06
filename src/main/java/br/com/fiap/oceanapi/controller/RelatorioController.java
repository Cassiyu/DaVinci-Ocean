package br.com.fiap.oceanapi.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.fiap.oceanapi.model.Relatorio;
import br.com.fiap.oceanapi.repository.RelatorioRepository;
import br.com.fiap.oceanapi.model.dto.RelatorioResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("relatorio")
@Slf4j
@CacheConfig(cacheNames = "relatorio")
@Tag(name = "relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioRepository relatorioRepository;

    @GetMapping
    @Cacheable
    @Operation(summary = "Listar Relatórios", description = "Retorna um array com todos os relatórios.")
    public List<RelatorioResponse> listAllRelatorios() {
        log.info("Listando todos os relatórios");
        List<Relatorio> relatorios = relatorioRepository.findAll();
        List<RelatorioResponse> relatorioResponses = new ArrayList<>();
        for (Relatorio relatorio : relatorios) {
            relatorioResponses.add(RelatorioResponse.fromRelatorio(relatorio));
        }
        return relatorioResponses;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Relatório criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Validação falhou. Verifique o corpo da requisição")
    })
    @CacheEvict(allEntries = true)
    public RelatorioResponse createRelatorio(@RequestBody Relatorio relatorio) {
        log.info("Cadastrando relatório {}", relatorio);
        Relatorio savedRelatorio = relatorioRepository.save(relatorio);
        return RelatorioResponse.fromRelatorio(savedRelatorio);
    }

}
