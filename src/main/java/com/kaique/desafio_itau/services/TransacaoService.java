package com.kaique.desafio_itau.services;

import com.kaique.desafio_itau.dto.TransacaoRequestDto;
import com.kaique.desafio_itau.exeptions.UnprocessableEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransacaoService {

    private final List<TransacaoRequestDto> listaTransacoes = new ArrayList<>();

    public void adicionarTransacao(TransacaoRequestDto transacaoRequestDto) {

        log.info("Iniciando o processo de gravar transações  " + transacaoRequestDto);

        long inicio = System.currentTimeMillis();

        if (transacaoRequestDto.dataHora().isAfter(OffsetDateTime.now())) {
            log.error("Data e hora da transação maiores do que a data e hora atuais");
            throw new UnprocessableEntity("Data e hora da transação não podem ser futuras");
        }
        if (transacaoRequestDto.valor() < 0) {
            log.error("Valor da transação menor que zero");
            throw new UnprocessableEntity("O Valor da transação não pode ser menor que zero");
        }

        listaTransacoes.add(transacaoRequestDto);
        long fim = System.currentTimeMillis();
        long tempo = fim - inicio;
        log.info("Transação {} gravada com sucesso em um periodo de {} milisegundos " + transacaoRequestDto, tempo);
    }

    public void limparTransacoes() {
        log.info("Iniciando o processo de deletar transações  ");
        long inicio = System.currentTimeMillis();
        listaTransacoes.clear();
        long fim = System.currentTimeMillis();
        long tempo = fim - inicio;
        log.info("Transações deletadas com sucesso em um periodo de {} milisegundos ", tempo);
    }

    public List<TransacaoRequestDto> buscarTransacoes(Integer intervaloBusca) {

        log.info("Iniciando o processo de buscar transações pelo periodo de tempo de {} segundos  ", intervaloBusca);
        OffsetDateTime dataHoraIntervalo = OffsetDateTime.now().minusSeconds(intervaloBusca);

        log.info("transações retornadas com sucesso pelo periodo de tempo de {} segundos  ", intervaloBusca);
        return listaTransacoes.stream()
                .filter(transacao -> transacao.dataHora().isAfter(dataHoraIntervalo))
                .toList();
    }

}
