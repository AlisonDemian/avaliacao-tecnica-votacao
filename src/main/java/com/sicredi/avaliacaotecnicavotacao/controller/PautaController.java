package com.sicredi.avaliacaotecnicavotacao.controller;

import com.sicredi.avaliacaotecnicavotacao.converter.PautaConverter;
import com.sicredi.avaliacaotecnicavotacao.dto.PautaRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.PautaResponseDto;
import com.sicredi.avaliacaotecnicavotacao.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/pautas")
public class PautaController {

    private final PautaService service;
    private final PautaConverter converter;

    @PostMapping("/criar")
    public ResponseEntity<PautaResponseDto> criarPauta(@Valid @RequestBody PautaRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(converter.entityToResponseDto(service.criar(converter.requestDtoToEntity(request))));
    }

    @GetMapping
    public ResponseEntity<List<PautaResponseDto>> listarPautas() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(converter.entityListToResponseDtoList(service.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PautaResponseDto> buscarPautaPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(converter.entityToResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PautaResponseDto> atualizarPauta(@PathVariable Long id,
                                                           @Valid @RequestBody PautaRequestDto request) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(converter.entityToResponseDto(
                        service.atualizar(id, converter.requestDtoToEntity(request))));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<PautaResponseDto> deletarPauta(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}