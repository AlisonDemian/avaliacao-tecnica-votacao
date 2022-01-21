package com.sicredi.avaliacaotecnicavotacao.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SessaoResponseDto {

    private Long id;
    private LocalDateTime tempoVotacao;
    private PautaResponseDto pauta;
    private boolean statusAberto;

}
