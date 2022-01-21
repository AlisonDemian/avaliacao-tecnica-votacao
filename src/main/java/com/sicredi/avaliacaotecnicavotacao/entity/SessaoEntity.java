package com.sicredi.avaliacaotecnicavotacao.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "sessao")
@Entity
public class SessaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tempo_votacao")
    private LocalDateTime tempoVotacao;

    @Column(name = "status_aberto")
    private boolean statusAberto;

    @OneToOne
    @JoinColumn(name = "id_pauta", referencedColumnName = "id")
    private PautaEntity pauta;
}
