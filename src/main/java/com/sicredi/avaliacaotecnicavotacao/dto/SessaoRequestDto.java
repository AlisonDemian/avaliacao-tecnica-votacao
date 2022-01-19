package com.sicredi.avaliacaotecnicavotacao.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SessaoRequestDto {

    @NotNull
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime tempoVotacao;

    @NotNull
    private Long idPauta;
}
