package com.sicredi.avaliacaotecnicavotacao.repository;

import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends JpaRepository<SessaoEntity, Long> {

    @Query("SELECT COUNT(*) = 0" +
            " FROM SessaoEntity se" +
            " WHERE se.pauta.id = :idPauta")
    boolean verificaSePautaNaoTemSessao(Long idPauta);
}
