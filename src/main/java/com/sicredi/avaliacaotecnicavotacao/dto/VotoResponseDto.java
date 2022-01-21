package com.sicredi.avaliacaotecnicavotacao.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VotoResponseDto {

    private SessaoResponseDto sessao;
    private AssociadoResponseDto associado;
    private String voto;
}
