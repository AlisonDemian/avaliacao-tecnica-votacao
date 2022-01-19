package com.sicredi.avaliacaotecnicavotacao.dto;

import lombok.*;

import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SessaoResponseDto {

    private Long id;
    private LocalTime tempoVotacao;
    private PautaResponseDto pauta;

}
