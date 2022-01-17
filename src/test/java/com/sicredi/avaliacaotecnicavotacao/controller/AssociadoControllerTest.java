package com.sicredi.avaliacaotecnicavotacao.controller;

import com.google.gson.Gson;
import com.sicredi.avaliacaotecnicavotacao.converter.AssociadoConverter;
import com.sicredi.avaliacaotecnicavotacao.dto.AssociadoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.entity.AssociadoEntity;
import com.sicredi.avaliacaotecnicavotacao.service.AssociadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.sicredi.avaliacaotecnicavotacao.utils.AssociadoUtils.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssociadoController.class)
class AssociadoControllerTest {

    private final Gson gson = new Gson();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociadoService service;

    @MockBean
    private AssociadoConverter converter;

    @Test
    void quandoCriarAssociado_retornaStatusCreated() throws Exception {
        AssociadoEntity entity = geraEntity();

        when(converter.requestDtoToEntity(any(AssociadoRequestDto.class)))
                .thenReturn(entity);
        when(service.criar(any(AssociadoEntity.class)))
                .thenReturn(entity);
        when(converter.entityToResponseDto(any(AssociadoEntity.class)))
                .thenReturn(geraResponseDto());

        MockHttpServletRequestBuilder requestBuilder = post("/associados/criar")
                .content(gson.toJson(geraRequestDto()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());

    }

    @Test
    void quandoListarAssociados_retornaStatusOk() throws Exception {
        when(service.listar())
                .thenReturn(geraEntityList());
        when(converter.entityListToResponseDtoList(anyList()))
                .thenReturn(geraResponseDtoList());

        MockHttpServletRequestBuilder requestBuilder = get("/associados")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoBuscarPorId_retornaStatusOk() throws Exception {
        when(service.buscarPorId(anyLong()))
                .thenReturn(geraEntity());
        when(converter.entityToResponseDto(any(AssociadoEntity.class)))
                .thenReturn(geraResponseDto());

        MockHttpServletRequestBuilder requestBuilder = get("/associados/{id}", anyLong())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoAtualizar_retornaStatusOk() throws Exception {
        AssociadoEntity entity = geraEntity();

        when(converter.requestDtoToEntity(any(AssociadoRequestDto.class)))
                .thenReturn(entity);
        when(service.atualizar(anyLong(), any(AssociadoEntity.class)))
                .thenReturn(entity);
        when(converter.entityToResponseDto(any(AssociadoEntity.class)))
                .thenReturn(geraResponseDto());

        MockHttpServletRequestBuilder requestBuilder = put("/associados/atualizar/{id}", anyLong())
                .content(gson.toJson(geraRequestDto()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoDeletar_retornaSucesso() throws Exception {
        Long id = anyLong();

        service.deletar(id);

        MockHttpServletRequestBuilder requestBuilder = delete("/associados/deletar/{id}", id)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }
}