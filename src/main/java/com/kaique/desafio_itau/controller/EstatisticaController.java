package com.kaique.desafio_itau.controller;

import com.kaique.desafio_itau.dto.EstatisticaResponseDto;
import com.kaique.desafio_itau.services.EstatisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/estatistica")
public class EstatisticaController {

    private final EstatisticaService estatisticaService;

    @GetMapping
    @Operation(description = "Endpoint responsável por buscar as estatísticas das transações")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca efetuada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na busca de estatísticas"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<EstatisticaResponseDto> buscarEstatistica(
            @RequestParam(value = "intervaloBusca", required = false,  defaultValue = "60") Integer intervaloBusca) {
        EstatisticaResponseDto estatisticaResponseDto = estatisticaService.calcularEstatistica(intervaloBusca);
        return ResponseEntity.ok(estatisticaResponseDto);
    }
}