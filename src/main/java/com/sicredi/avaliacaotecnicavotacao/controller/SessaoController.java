package com.sicredi.avaliacaotecnicavotacao.controller;

import com.sicredi.avaliacaotecnicavotacao.business.SessaoBusiness;
import com.sicredi.avaliacaotecnicavotacao.converter.SessaoConverter;
import com.sicredi.avaliacaotecnicavotacao.dto.SessaoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.SessaoResponseDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "sessao")
@RequestMapping(path = "/sessoes")
public class SessaoController {

    private final SessaoBusiness business;
    private final SessaoConverter converter;

    @PostMapping("/criar")
    public ResponseEntity<SessaoResponseDto> criar(@Valid @RequestBody SessaoRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(converter.entityToResponseDto(business.criar(converter.requestToEntity(request))));
    }

    @GetMapping
    public ResponseEntity<List<SessaoResponseDto>> listar() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(converter.entityListToResponseDtoList(business.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SessaoResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(converter.entityToResponseDto(business.buscarPorId(id)));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<SessaoResponseDto> atualizar(@PathVariable Long id,
                                                       @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                                       @RequestParam(name = "tempoVotacao")
                                                               LocalDateTime tempoVotacao) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(converter.entityToResponseDto(business.atualizar(id, tempoVotacao)));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<SessaoResponseDto> deletar(@PathVariable Long id) {
        business.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
