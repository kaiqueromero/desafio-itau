package com.kaique.desafio_itau.controller;

import com.kaique.desafio_itau.dto.TransacaoRequestDto;
import com.kaique.desafio_itau.services.TransacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Endpoint responsável por adicionar uma transação")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Transação adicionada com sucesso"),
            @ApiResponse(responseCode = "422", description = "Erro de validação, dados inválidos"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição, dados inválidos"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> adicionarTransacao(@RequestBody TransacaoRequestDto transacaoRequestDto) {
        transacaoService.adicionarTransacao(transacaoRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    @Operation(description = "Endpoint responsável por deletar transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Transações deletadas com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro de requisição"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<Void> deletarTransacoes() {
        transacaoService.limparTransacoes();
        return ResponseEntity.ok().build();
    }
}
