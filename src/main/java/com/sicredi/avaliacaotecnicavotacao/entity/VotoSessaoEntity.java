package com.sicredi.avaliacaotecnicavotacao.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "voto_sessao_associado")
@Entity
public class VotoSessaoEntity {

    @EmbeddedId
    private VotoSessaoEntityKey id;
    private Integer voto;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idSessao")
    @JoinColumn(name = "id_sessao")
    private SessaoEntity sessao;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idAssociado")
    @JoinColumn(name = "id_associado")
    private AssociadoEntity associado;
}
