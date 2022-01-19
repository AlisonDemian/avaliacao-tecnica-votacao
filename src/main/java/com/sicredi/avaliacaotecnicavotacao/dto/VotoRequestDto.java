package com.sicredi.avaliacaotecnicavotacao.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VotoRequestDto {

    @NotNull
    private Integer voto;
    @NotNull
    private Long idSessao;
    @NotNull
    private Long idAssociado;
}
