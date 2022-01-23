package com.sicredi.avaliacaotecnicavotacao.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sicredi.avaliacaotecnicavotacao.business.VotacaoBusiness;
import com.sicredi.avaliacaotecnicavotacao.converter.VotoConverter;
import com.sicredi.avaliacaotecnicavotacao.dto.VotoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static com.sicredi.avaliacaotecnicavotacao.utils.VotoUtils.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(VotoController.class)
class VotoControllerTest {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();
    @MockBean
    private VotacaoBusiness votacaoBusiness;

    @MockBean
    private VotoConverter votoConverter;

    @Test
    void quandoVotar_retornaStatusOk() throws Exception {
        when(votoConverter.requestDtoToEntity(any(VotoRequestDto.class)))
                .thenReturn(geraVotoEntity());
        when(votacaoBusiness.votar(any(VotoSessaoEntity.class)))
                .thenReturn(geraVotoEntity());
        when(votoConverter.entityToResponseDto(any(VotoSessaoEntity.class)))
                .thenReturn(geraVotoSimResponseDto());

        MockHttpServletRequestBuilder requestBuilder = post("/votacao/votar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(geraVotoRequestDto()));

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk());
    }
}