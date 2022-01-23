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

    @NotNull(message = "Campo voto é obrigatório")
    @Min(value = 0, message = "O mínimo é 0")
    @Max(value = 1, message = "O máximo é 1")
    private Integer voto;
    @NotNull(message = "Campo sessao é obrigatório")
    private Long idSessao;
    @NotNull(message = "Campo associado é obrigatório")
    private Long idAssociado;
}
