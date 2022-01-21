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

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd-HH-mm-ss")
    private LocalDateTime tempoVotacao;

    @NotNull
    private Long idPauta;
}
