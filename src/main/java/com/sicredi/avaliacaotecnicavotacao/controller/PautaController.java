package com.sicredi.avaliacaotecnicavotacao.controller;

import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.service.PautaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/pauta")
public class PautaController {

    private final PautaService service;

    @PostMapping
    public ResponseEntity<PautaEntity> criarPauta(@RequestBody PautaEntity request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.criar(request));

    }
}
