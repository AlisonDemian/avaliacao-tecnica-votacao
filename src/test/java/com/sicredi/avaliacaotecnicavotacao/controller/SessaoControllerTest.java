package com.sicredi.avaliacaotecnicavotacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.avaliacaotecnicavotacao.converter.SessaoConverter;
import com.sicredi.avaliacaotecnicavotacao.dto.SessaoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
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

import static com.sicredi.avaliacaotecnicavotacao.utils.SessaoUtils.*;
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
    private SessaoConverter converter;

    @BeforeEach
    void init() {
        mapper.findAndRegisterModules(); //necessario para serialização do LocalTime pelo ObjectMapper
    }

    @Test
    void quandoCriarSessao_retornarStatusCreated() throws Exception {

        when(converter.requestToEntity(any(SessaoRequestDto.class)))
                .thenReturn(SessaoUtils.geraSessaoEntity());
        when(service.criar(any(SessaoEntity.class)))
                .thenReturn(SessaoUtils.geraSessaoEntity());
        when(converter.entityToResponseDto(any(SessaoEntity.class)))
                .thenReturn(SessaoUtils.geraSessaoResponseDto());

        MockHttpServletRequestBuilder requestBuilder = post("/sessoes/criar")
                .content(mapper.writeValueAsString(SessaoUtils.geraSessaoRequestDto()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @Test
    void quandoListarSessoes_retornarStatusOk() throws Exception {
        when(service.listar())
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
        when(service.buscarPorId(id))
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
        when(converter.requestToEntity(any(SessaoRequestDto.class)))
                .thenReturn(geraSessaoEntity());
        when(service.atualizar(anyLong(), any(SessaoEntity.class)))
                .thenReturn(geraSessaoEntity());
        when(converter.entityToResponseDto(any(SessaoEntity.class)))
                .thenReturn(geraSessaoResponseDto());

        MockHttpServletRequestBuilder requestBuilder = put("/sessoes/atualizar/{id}", anyLong())
                .content(mapper.writeValueAsString(geraSessaoRequestDto()))
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