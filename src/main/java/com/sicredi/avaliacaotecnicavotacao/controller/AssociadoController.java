package com.sicredi.avaliacaotecnicavotacao.controller;

import com.sicredi.avaliacaotecnicavotacao.converter.AssociadoConverter;
import com.sicredi.avaliacaotecnicavotacao.dto.AssociadoRequestDto;
import com.sicredi.avaliacaotecnicavotacao.dto.AssociadoResponseDto;
import com.sicredi.avaliacaotecnicavotacao.service.AssociadoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Tag(name = "associado")
@RequestMapping(path = "/associados")
public class AssociadoController {

    private final AssociadoService service;
    private final AssociadoConverter converter;

    @PostMapping("/criar")
    public ResponseEntity<AssociadoResponseDto> criar(@Valid @RequestBody AssociadoRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(converter.entityToResponseDto(
                        service.criar(converter.requestDtoToEntity(requestDto))));
    }

    @GetMapping
    public ResponseEntity<List<AssociadoResponseDto>> listar() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(converter.entityListToResponseDtoList(service.listar()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociadoResponseDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(converter.entityToResponseDto(service.buscarPorId(id)));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AssociadoResponseDto> atualizar(@PathVariable Long id,
                                                          @Valid @RequestBody AssociadoRequestDto requestDto) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(converter.entityToResponseDto(service.atualizar(id, converter.requestDtoToEntity(requestDto))));
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<AssociadoResponseDto> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
