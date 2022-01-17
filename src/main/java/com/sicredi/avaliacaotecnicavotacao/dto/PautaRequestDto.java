package com.sicredi.avaliacaotecnicavotacao.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PautaRequestDto {

    @NotBlank(message = "tema é obrigatório")
    private String tema;
}
