package com.sicredi.avaliacaotecnicavotacao.repository;

import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SessaoRepository extends JpaRepository<SessaoEntity, Long> {

    List<SessaoEntity> findByStatusAberto(Boolean status);

    @Query("SELECT COUNT(*) = 0" +
            " FROM SessaoEntity se" +
            " WHERE se.pauta.id = :idPauta")
    boolean verificaSePautaNaoTemSessao(Long idPauta);
}
