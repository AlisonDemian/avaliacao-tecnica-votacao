package com.sicredi.avaliacaotecnicavotacao.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@Builder
@Embeddable
public class VotoSessaoEntityKey implements Serializable {

    @Column(name = "id_sessao")
    private Long idSessao;

    @Column(name = "id_associado")
    private Long idAssociado;
}
