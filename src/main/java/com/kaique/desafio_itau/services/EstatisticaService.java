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

        log.info("Iniciando o processo de calcular estatísticas em um periodo de tempo de {} segundos", intervaloBusca);

        long inicio = System.currentTimeMillis();

        List<TransacaoRequestDto>  transacoes = transacaoService.buscarTransacoes(intervaloBusca);
        DoubleSummaryStatistics estatisticas = transacoes.stream()
                .mapToDouble(TransacaoRequestDto::valor)
                .summaryStatistics();

        if (transacoes.isEmpty()) {
            return new EstatisticaResponseDto(0L, 0.0, 0.0, 0.0, 0.0);
        }

        long fim = System.currentTimeMillis();
        long tempo = fim - inicio;
        log.info("Estatísticas calculadas com sucesso em um periodo de {} milisegundos", tempo);

        return new EstatisticaResponseDto(
                estatisticas.getCount(),
                estatisticas.getSum(),
                estatisticas.getAverage(),
                estatisticas.getMin(),
                estatisticas.getMax()
        );
    }
}
