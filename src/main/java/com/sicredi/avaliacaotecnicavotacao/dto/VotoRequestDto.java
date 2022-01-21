package com.sicredi.avaliacaotecnicavotacao.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class VotoRequestDto {

    @NotNull
    @Min(0)
    @Max(1)
    private Integer voto;
    @NotNull
    private Long idSessao;
    @NotNull
    private Long idAssociado;
}
