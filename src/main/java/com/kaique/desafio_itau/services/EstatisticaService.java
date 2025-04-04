package com.kaique.desafio_itau.services;

import com.kaique.desafio_itau.dto.EstatisticaResponseDto;
import com.kaique.desafio_itau.dto.TransacaoRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticaService {

    public final TransacaoService transacaoService;

    public EstatisticaResponseDto calcularEstatistica(Integer intervaloBusca) {

        log.info("Iniciando o processo de calcular estatísticas pelo periodo de tempo de {} segundos", intervaloBusca);

        List<TransacaoRequestDto>  transacoes = transacaoService.buscarTransacoes(intervaloBusca);
        DoubleSummaryStatistics estatisticas = transacoes.stream()
                .mapToDouble(TransacaoRequestDto::valor)
                .summaryStatistics();

        if (transacoes.isEmpty()) {
            return new EstatisticaResponseDto(0L, 0.0, 0.0, 0.0, 0.0);
        }

        log.info("Estatísticas calculadas com sucesso");

        return new EstatisticaResponseDto(
                estatisticas.getCount(),
                estatisticas.getSum(),
                estatisticas.getAverage(),
                estatisticas.getMin(),
                estatisticas.getMax()
        );
    }
}
