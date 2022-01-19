package com.sicredi.avaliacaotecnicavotacao.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

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
    private LocalTime tempoVotacao;

    @OneToOne
    @JoinColumn(name = "id_pauta", referencedColumnName = "id")
    private PautaEntity pauta;
}
