package com.sicredi.avaliacaotecnicavotacao.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PautaRequestDto {
    @Size(max = 100, message = "Tema pode ter até 100 caracteres")
    @NotBlank(message = "tema é obrigatório")
    private String tema;
}
