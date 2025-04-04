package com.kaique.desafio_itau.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kaique.desafio_itau.dto.TransacaoRequestDto;
import com.kaique.desafio_itau.exeptions.UnprocessableEntity;
import com.kaique.desafio_itau.services.TransacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class TransacaoControllerTest {

    @InjectMocks
    private TransacaoController transacaoController;

    @Mock
    private TransacaoService transacaoService;

    TransacaoRequestDto transacao;

    MockMvc mockMvc;

    @Autowired
    final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
        mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
        transacao = new TransacaoRequestDto(20.0, OffsetDateTime.of(2025, 4, 1, 10, 1, 1, 0, ZoneOffset.UTC));
    }

    @Test
    void deveAdicionarTransacaoComSucesso() throws Exception {

        doNothing().when(transacaoService).adicionarTransacao(transacao);

        mockMvc.perform(post("/transacao")
                        .content(objectMapper.writeValueAsString(transacao))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    void deveLancarExcecaoAoAdicionarTransacao()  throws Exception {

        doThrow(new UnprocessableEntity("Erro de requisição")).when(transacaoService).adicionarTransacao(transacao);

        mockMvc.perform(post("/transacao")
                        .content(objectMapper.writeValueAsString(transacao))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void deletarTransacoesComSucesso() throws Exception {
        doNothing().when(transacaoService).limparTransacoes();

        mockMvc.perform(delete("/transacao"))
                .andExpect(status().isOk());
    }
}