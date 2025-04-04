package com.kaique.desafio_itau.services;

import com.kaique.desafio_itau.dto.EstatisticaResponseDto;
import com.kaique.desafio_itau.dto.TransacaoRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EstatisticaServiceTest {

    @InjectMocks
    private EstatisticaService estatisticaService;

    @Mock
    private TransacaoService transacaoService;

    TransacaoRequestDto transacao;
    EstatisticaResponseDto estatistica;

    @BeforeEach
    void setUp() {
        transacao = new TransacaoRequestDto(20.0, OffsetDateTime.now());
        estatistica = new EstatisticaResponseDto(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void calcularEstatisticaComSucesso() {

        Integer intervaloBusca = 60;
        when(transacaoService.buscarTransacoes(intervaloBusca)).thenReturn(Collections.singletonList(transacao));

        EstatisticaResponseDto resultado = estatisticaService.calcularEstatistica(intervaloBusca);
        verify(transacaoService, times(1)).buscarTransacoes(intervaloBusca);

        Assertions.assertThat(resultado).usingRecursiveComparison()
                .isEqualTo(estatistica);
    }

    @Test
    void calcularEstatisticaQuandoListaVazia() {

        EstatisticaResponseDto estatisticaEsperada = new EstatisticaResponseDto(0L, 0.0, 0.0, 0.0, 0.0);

        Integer intervaloBusca = 60;
        when(transacaoService.buscarTransacoes(intervaloBusca)).thenReturn(Collections.emptyList());

        EstatisticaResponseDto resultado = estatisticaService.calcularEstatistica(intervaloBusca);
        verify(transacaoService, times(1)).buscarTransacoes(intervaloBusca);

        Assertions.assertThat(resultado).usingRecursiveComparison()
                .isEqualTo(estatisticaEsperada);
    }
}
