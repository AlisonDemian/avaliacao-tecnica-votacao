package com.sicredi.avaliacaotecnicavotacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.avaliacaotecnicavotacao.business.SessaoBusiness;
import com.sicredi.avaliacaotecnicavotacao.converter.SessaoConverter;
import com.sicredi.avaliacaotecnicavotacao.dto.SessaoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.SessaoResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.exception.BusinessGenericException;
import com.sicredi.avaliacaotecnicavotacao.service.SessaoService;
import com.sicredi.avaliacaotecnicavotacao.utils.SessaoUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.sicredi.avaliacaotecnicavotacao.utils.SessaoUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SessaoController.class)
class SessaoControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessaoService service;

    @MockBean
    private SessaoBusiness business;

    @MockBean
    private SessaoConverter converter;

    @BeforeEach
    void init() {
        mapper.findAndRegisterModules(); //necessario para serialização do LocalTime pelo ObjectMapper
    }

    @Test
    void quandoCriarSessao_retornarStatusCreated() throws Exception {

        SessaoEntity sessaoEntity = geraSessaoEntity();
        when(converter.requestToEntity(any(SessaoRequestDto.class)))
                .thenReturn(sessaoEntity);
        when(business.criar(any(SessaoEntity.class)))
                .thenReturn(sessaoEntity);
        when(converter.entityToResponseDto(any(SessaoEntity.class)))
                .thenReturn(SessaoUtils.geraSessaoResponseDto());

        MockHttpServletRequestBuilder requestBuilder = post("/sessoes/criar")
                .content(mapper.writeValueAsString(SessaoUtils.geraSessaoRequestDto()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @Test
    void quandoCriarSessao_throwBusinessGenericException() throws Exception {
        SessaoEntity sessaoEntity = geraSessaoEntity();
        when(converter.requestToEntity(any(SessaoRequestDto.class)))
                .thenReturn(sessaoEntity);
        when(business.criar(any(SessaoEntity.class)))
                .thenThrow(new BusinessGenericException(String.format("pauta %d ja foi votada", sessaoEntity.getPauta().getId())));

        MockHttpServletRequestBuilder requestBuilder = post("/sessoes/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(geraSessaoRequestDto()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(resultado -> assertEquals(BusinessGenericException.class, resultado.getResolvedException().getClass()))
                .andExpect(resultado -> assertTrue(resultado.getResolvedException().getMessage().contains(String.format("pauta %d ja foi votada", sessaoEntity.getPauta().getId()))));


    }

    @Test
    void quandoListarSessoes_retornarStatusOk() throws Exception {
        when(business.listar())
                .thenReturn(geraListaSessaoEntity());
        when(converter.entityListToResponseDtoList(anyList()))
                .thenReturn(geraListaSessaoResponseDto());

        MockHttpServletRequestBuilder requestBuilder = get("/sessoes")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoBuscarPorId_retornarStatusOk() throws Exception {
        Long id = anyLong();
        when(business.buscarPorId(id))
                .thenReturn(geraSessaoEntity());
        when(converter.entityToResponseDto(any(SessaoEntity.class)))
                .thenReturn(geraSessaoResponseDto());

        MockHttpServletRequestBuilder requestBuilder = get("/sessoes/{id}", id)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());

    }

    @Test
    void quandoAtualizar_retornarStatusOk() throws Exception {
        LocalDateTime tempo = LocalDateTime.now().plusYears(1);
        String tempoFormatado = tempo.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        SessaoEntity sessaoAtualizada = geraSessaoEntity();
        sessaoAtualizada.setTempoVotacao(tempo);
        SessaoResponseDto sessaoResponseDtoAtualizada = geraSessaoResponseDto();
        sessaoResponseDtoAtualizada.setTempoVotacao(tempo);

        Long id = anyLong();
        when(business.atualizar(id, any(LocalDateTime.class)))
                .thenReturn(sessaoAtualizada);
        when(converter.entityToResponseDto(any(SessaoEntity.class)))
                .thenReturn(sessaoResponseDtoAtualizada);

        MockHttpServletRequestBuilder requestBuilder = put("/sessoes/atualizar/{id}", id)
                .param("tempoVotacao", tempoFormatado)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoDeletar_retornarStatusNoContent() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = delete("/sessoes/deletar/{id}", anyLong())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }
}