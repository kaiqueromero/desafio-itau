package com.kaique.desafio_itau.services;

import com.kaique.desafio_itau.dto.EstatisticaResponseDto;
import com.kaique.desafio_itau.dto.TransacaoRequestDto;
import com.kaique.desafio_itau.exeptions.UnprocessableEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoServiceTest {

    @InjectMocks
    private TransacaoService transacaoService;

    TransacaoRequestDto transacao;
    EstatisticaResponseDto estatistica;

    @BeforeEach
    void setUp() {
        transacao = new TransacaoRequestDto(20.0, OffsetDateTime.now());
    }

    @Test
    void transacaoAdicionadaComSucesso() {

        transacaoService.adicionarTransacao(transacao);

        List<TransacaoRequestDto> transacoes = transacaoService.buscarTransacoes(50000);

        assertTrue(transacoes.contains(transacao));
    }

    @Test
    void deveLançarExcecaoValorNegativo () {

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacao(new TransacaoRequestDto(-20.0, OffsetDateTime.now())));

        assertEquals("O Valor da transação não pode ser menor que zero", exception.getMessage());
    }

    @Test
    void deveLançarExcecaoDataOuHoraMaiorQueAtual() {

        UnprocessableEntity exception = assertThrows(UnprocessableEntity.class,
                () -> transacaoService.adicionarTransacao(new TransacaoRequestDto(20.0, OffsetDateTime.now().plusDays(1))));

        assertEquals("Data e hora da transação não podem ser futuras", exception.getMessage());
    }

    @Test
    void transacoesExcluidasComSucesso() {

        transacaoService.adicionarTransacao(transacao);

        transacaoService.limparTransacoes();

        List<TransacaoRequestDto> transacoes = transacaoService.buscarTransacoes(50000);

        assertTrue(transacoes.isEmpty());
    }

    @Test
    void buscaTransacoesEmIntervalo() {

        TransacaoRequestDto dto = new TransacaoRequestDto(30.0, OffsetDateTime.now().minusSeconds(120));

        transacaoService.adicionarTransacao(transacao);
        transacaoService.adicionarTransacao(dto);

        List<TransacaoRequestDto> transacoes = transacaoService.buscarTransacoes(60);

        assertTrue(transacoes.contains(transacao));
        assertFalse(transacoes.contains(dto));
    }
}
