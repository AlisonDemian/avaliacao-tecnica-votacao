package com.sicredi.avaliacaotecnicavotacao.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PautaVotadaMsgResponseDto {

    private Long id;
    private String tema;
    private String statusVotacao;
    private Integer votosSim;
    private Integer votosNao;

    @Override
    public String toString() {
        return "id: " + id+ "\ntema: " + tema + "\nstatus: " + statusVotacao + "\nvotos sim: " + votosSim + "\nvotos nao: " + votosNao;
    }
}
