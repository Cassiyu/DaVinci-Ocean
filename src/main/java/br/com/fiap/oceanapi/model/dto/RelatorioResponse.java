package br.com.fiap.oceanapi.model.dto;

import br.com.fiap.oceanapi.model.Relatorio;
import java.time.LocalDateTime;

public record RelatorioResponse(
        Long relatorio_id,
        LocalDateTime data_inicio,
        LocalDateTime data_fim,
        String localizacao,
        Double temperatura_media,
        Double temperatura_maxima,
        Double temperatura_minima) {

    public static RelatorioResponse fromRelatorio(Relatorio relatorio) {
        return new RelatorioResponse(
            relatorio.getRelatorio_id(),
            relatorio.getData_inicio(),
            relatorio.getData_fim(),
            relatorio.getLocalizacao(),
            relatorio.getTemperatura_media(),
            relatorio.getTemperatura_maxima(),
            relatorio.getTemperatura_minima()
        );
    }

}
