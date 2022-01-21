package com.sicredi.avaliacaotecnicavotacao.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "pauta")
@Entity
public class PautaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tema;

    @OneToOne(mappedBy = "pauta")
    private SessaoEntity sessao;

    @Column(name = "status_votacao")
    private String statusVotacao;

    @Column(name = "votos_sim")
    private Integer votosSim = 0;

    @Column(name = "votos_nao")
    private Integer votosNao = 0;
}
