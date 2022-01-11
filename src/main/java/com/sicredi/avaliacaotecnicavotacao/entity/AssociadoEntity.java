package com.sicredi.avaliacaotecnicavotacao.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "associado")
@Entity
public class AssociadoEntity {

    @Id
    private Long id;

    private String cpf;
}
