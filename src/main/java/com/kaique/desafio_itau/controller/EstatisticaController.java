package com.kaique.desafio_itau.controller;

import com.kaique.desafio_itau.dto.EstatisticaResponseDto;
import com.kaique.desafio_itau.services.EstatisticaService;
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
    public ResponseEntity<EstatisticaResponseDto> buscarEstatistica(
            @RequestParam(value = "intervaloBusca", required = false,  defaultValue = "60") Integer intervaloBusca) {
        EstatisticaResponseDto estatisticaResponseDto = estatisticaService.calcularEstatistica(intervaloBusca);
        return ResponseEntity.ok(estatisticaResponseDto);
    }
}