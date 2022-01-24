package com.sicredi.avaliacaotecnicavotacao.controller;

import com.sicredi.avaliacaotecnicavotacao.business.VotacaoBusiness;
import com.sicredi.avaliacaotecnicavotacao.converter.VotoConverter;
import com.sicredi.avaliacaotecnicavotacao.dto.VotoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.VotoResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Tag(name = "voto")
@RequestMapping(path = "/votacao")
public class VotoController {

    private final VotacaoBusiness votacaoBusiness;
    private final VotoConverter converter;

    @PostMapping("/votar")
    public ResponseEntity<VotoResponseDto> votar(@Valid @RequestBody VotoRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(converter.entityToResponseDto(votacaoBusiness.votar(converter.requestDtoToEntity(requestDto))));
    }
}
