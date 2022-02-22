package com.sicredi.avaliacaotecnicavotacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.avaliacaotecnicavotacao.converter.PautaConverter;
import com.sicredi.avaliacaotecnicavotacao.dto.PautaRequestDto;
import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.service.PautaService;
import com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.sicredi.avaliacaotecnicavotacao.utils.PautaUtils.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PautaController.class)
class PautaControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PautaConverter converter;

    @MockBean
    private PautaService service;

    @Test
    void quando_criarPauta_retornaStatusCreated() throws Exception {
        PautaEntity entity = geraPautaAbertaEntity();

        when(converter.requestDtoToEntity(any(PautaRequestDto.class)))
                .thenReturn(entity);
        when(service.criar(any(PautaEntity.class)))
                .thenReturn(entity);
        when(converter.entityToResponseDto(any(PautaEntity.class)))
                .thenReturn(geraPautaResponse());

        MockHttpServletRequestBuilder requestBuilder = post("/pautas/criar", any(PautaRequestDto.class))
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(geraPautaRequest()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());
    }

    @Test
    void quando_listarPautas_retornaStatusOk() throws Exception {
        when(service.listar(anyInt(), anyInt()))
                .thenReturn(geraPagePautaEntity());
        when(converter.entityPageToResponseDtoPage(any()))
                .thenReturn(PautaUtils.geraPagePautaResponse());

        MockHttpServletRequestBuilder requestBuilder = get("/pautas/")
                .param("page", Integer.valueOf(0).toString())
                .param("size", Integer.valueOf(1).toString())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quando_buscarPautaPorId_retornaStatusOk() throws Exception {
        when(service.buscarPorId(anyLong()))
                .thenReturn(geraPautaAbertaEntity());
        when(converter.entityToResponseDto(any(PautaEntity.class)))
                .thenReturn(geraPautaResponse());

        MockHttpServletRequestBuilder requestBuilder = get("/pautas/{id}", anyLong())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quando_atualizarPauta_retornaStatusOk() throws Exception {
        when(converter.requestDtoToEntity(any(PautaRequestDto.class)))
                .thenReturn(geraPautaAbertaEntity());
        when(service.atualizar(anyLong(), any(PautaEntity.class)))
                .thenReturn(geraPautaAbertaEntity());
        when(converter.entityToResponseDto(any(PautaEntity.class)))
                .thenReturn(geraPautaResponse());

        MockHttpServletRequestBuilder requestBuilder = put("/pautas/atualizar/{id}",
                anyLong())
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(geraPautaRequest()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quando_deletarPauta_retornaSucesso() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = delete("/pautas/deletar/{id}", anyLong())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNoContent());
    }
}