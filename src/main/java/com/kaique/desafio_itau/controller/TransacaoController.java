package com.kaique.desafio_itau.controller;

import com.kaique.desafio_itau.dto.TransacaoRequestDto;
import com.kaique.desafio_itau.services.TransacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/transacao")
public class TransacaoController {

    private final TransacaoService transacaoService;

    @PostMapping
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDto transacaoRequestDto) {
        transacaoService.adicionarTransacao(transacaoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarTransacoes() {
        transacaoService.limparTransacoes();
        return ResponseEntity.ok().build();
    }
}
