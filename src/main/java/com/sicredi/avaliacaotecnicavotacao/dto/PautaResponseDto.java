package com.sicredi.avaliacaotecnicavotacao.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PautaResponseDto {

    private Long id;
    private String tema;
    private String statusVotacao;
}
