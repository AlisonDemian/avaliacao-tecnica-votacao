package com.sicredi.avaliacaotecnicavotacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SessaoRequestDto {

    @NotNull(message = "Campo tempoVotacao é obrigatório")
    @JsonFormat(pattern = "yyyy-MM-dd-HH-mm-ss")
    private LocalDateTime tempoVotacao;

    @NotNull(message = "Campo idPauta é obrigatório")
    private Long idPauta;
}
