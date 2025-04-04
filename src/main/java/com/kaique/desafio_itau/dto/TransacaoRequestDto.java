package com.kaique.desafio_itau.dto;

import java.time.OffsetDateTime;

public record TransacaoRequestDto(Double valor, OffsetDateTime dataHora) {
}
