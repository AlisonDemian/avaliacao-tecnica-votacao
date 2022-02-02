package com.sicredi.avaliacaotecnicavotacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.avaliacaotecnicavotacao.converter.AssociadoConverter;
import com.sicredi.avaliacaotecnicavotacao.dto.AssociadoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.entity.AssociadoEntity;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementAlreadyExistsException;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementNotFoundException;
import com.sicredi.avaliacaotecnicavotacao.service.AssociadoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.client.HttpClientErrorException;

import static com.sicredi.avaliacaotecnicavotacao.utils.AssociadoUtils.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AssociadoController.class)
class AssociadoControllerTest {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociadoService service;

    @MockBean
    private AssociadoConverter converter;

    @Test
    void quandoCriarAssociado_retornaStatusCreated() throws Exception {
        AssociadoEntity entity = geraAssociadoEntity();

        when(converter.requestDtoToEntity(any(AssociadoRequestDto.class)))
                .thenReturn(entity);
        when(service.criar(any(AssociadoEntity.class)))
                .thenReturn(entity);
        when(converter.entityToResponseDto(any(AssociadoEntity.class)))
                .thenReturn(geraAssociadoResponseDto());

        MockHttpServletRequestBuilder requestBuilder = post("/associados/criar")
                .content(mapper.writeValueAsString(geraAssociadoRequestDto()))
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());

    }

    @Test
    void quandoCriarAssociado_throwMethodArgumentNotValidException() throws Exception {
        AssociadoRequestDto requestDto = geraAssociadoRequestDto();
        requestDto.setCpf(" ");

        MockHttpServletRequestBuilder requestBuilder = post("/associados/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(requestDto));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(resultado -> assertEquals(MethodArgumentNotValidException.class, resultado.getResolvedException().getClass()))
                .andExpect(resultado -> assertTrue(resultado.getResolvedException().getMessage().contains("CPF é obrigatório")));
    }

    @Test
    void quandoCriarAssociado_throwElementAlreadyExistsException() throws Exception {
        AssociadoEntity entity = geraAssociadoEntity();
        String errorMsg = String.format("Associado com o CPF %s já registrado", entity.getCpf());

        when(converter.requestDtoToEntity(any(AssociadoRequestDto.class)))
                .thenReturn(entity);
        when(service.criar(any(AssociadoEntity.class)))
                .thenThrow(new ElementAlreadyExistsException(errorMsg));

        MockHttpServletRequestBuilder requestBuilder = post("/associados/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(geraAssociadoRequestDto()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isBadRequest())
                .andExpect(resultado -> assertEquals(ElementAlreadyExistsException.class,
                        resultado.getResolvedException().getClass()))
                .andExpect(resultado -> assertEquals(String.format("Associado com o CPF %s já registrado",
                        entity.getCpf()), resultado.getResolvedException().getMessage()));
    }

    @Test
    void quandoCriarAssociado_throwHttpClientErrorException() throws Exception{
        when(converter.requestDtoToEntity(any(AssociadoRequestDto.class)))
                .thenReturn(geraAssociadoEntity());
        when(service.criar(any(AssociadoEntity.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        MockHttpServletRequestBuilder requestBuilder = post("/associados/criar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(geraAssociadoRequestDto()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(resultado -> assertEquals(HttpClientErrorException.class,
                        resultado.getResolvedException().getClass()));
    }

    @Test
    void quandoListarAssociados_retornaStatusOk() throws Exception {
        when(service.listar())
                .thenReturn(geraAssociadoEntityList());
        when(converter.entityListToResponseDtoList(anyList()))
                .thenReturn(geraAssociadoResponseDtoList());

        MockHttpServletRequestBuilder requestBuilder = get("/associados")
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoBuscarPorId_retornaStatusOk() throws Exception {
        when(service.buscarPorId(anyLong()))
                .thenReturn(geraAssociadoEntity());
        when(converter.entityToResponseDto(any(AssociadoEntity.class)))
                .thenReturn(geraAssociadoResponseDto());

        MockHttpServletRequestBuilder requestBuilder = get("/associados/{id}", anyLong())
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }

    @Test
    void quandoCriarAssociado_throwElementNotFoundException() throws Exception {
        Long id = anyLong();
        when(service.buscarPorId(id))
                .thenThrow(new ElementNotFoundException(String.format("Associado com id %d não encontrado", id)));

        MockHttpServletRequestBuilder requestBuilder = get("/associados/{id}", id)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound())
                .andExpect(resultado -> assertEquals(ElementNotFoundException.class, resultado.getResolvedException().getClass()))
                .andExpect(resultado -> assertEquals(String.format("Associado com id %d não encontrado", id),
                        resultado.getResolvedException().getMessage()));
    }

    @Test
    void quandoAtualizar_retornaStatusOk() throws Exception {
        AssociadoEntity entity = geraAssociadoEntity();

        when(converter.requestDtoToEntity(any(AssociadoRequestDto.class)))
                .thenReturn(entity);
        when(service.atualizar(anyLong(), any(AssociadoEntity.class)))
                .thenReturn(entity);
        when(converter.entityToResponseDto(any(AssociadoEntity.class)))
                .thenReturn(geraAssociadoResponseDto());

        MockHttpServletRequestBuilder requestBuilder = put("/associados/atualizar/{id}", anyLong())
                .content(mapper.writeValueAsString(geraAssociadoRequestDto()))
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