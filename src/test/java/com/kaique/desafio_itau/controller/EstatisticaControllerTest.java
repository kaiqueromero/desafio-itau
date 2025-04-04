package com.kaique.desafio_itau.controller;

import com.kaique.desafio_itau.dto.EstatisticaResponseDto;
import com.kaique.desafio_itau.services.EstatisticaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class EstatisticaControllerTest {

    @InjectMocks
    private EstatisticaController estatisticaController;

    @Mock
    private EstatisticaService estatisticaService;

    EstatisticaResponseDto estatisticas;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(estatisticaController).build();
        estatisticas = new EstatisticaResponseDto(1L, 20.0, 20.0, 20.0, 20.0);
    }

    @Test
    void buscarEstatisticaComSucesso() throws Exception {

        when(estatisticaService.calcularEstatistica(60)).thenReturn(estatisticas);

        mockMvc.perform(get("/estatistica")
                        .param("intervaloBusca", "60"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.count").value(1L))
                .andExpect(jsonPath("$.sum").value(20.0))
                .andExpect(jsonPath("$.avg").value(20.0))
                .andExpect(jsonPath("$.min").value(20.0))
                .andExpect(jsonPath("$.max").value(20.0));
    }
}