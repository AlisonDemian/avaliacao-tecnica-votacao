package com.sicredi.avaliacaotecnicavotacao.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VotoResponseDto {

    private Long id;
    private SessaoResponseDto sessao;
    private AssociadoResponseDto associado;
}
