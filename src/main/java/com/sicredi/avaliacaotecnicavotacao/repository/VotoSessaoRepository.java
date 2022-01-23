package com.sicredi.avaliacaotecnicavotacao.repository;

import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface VotoSessaoRepository extends JpaRepository<VotoSessaoEntity, Long> {

    @Query("SELECT COUNT(*) > 0" +
            " FROM VotoSessaoEntity vts" +
            " JOIN vts.sessao" +
            " JOIN vts.associado" +
            " WHERE vts.sessao.id = :idSessao" +
            " AND vts.associado.id = :idAssociado")
    boolean verificaVotoMultiplo(Long idAssociado, Long idSessao);

    @Transactional
    void deleteBySessaoId(Long idSessao);
}
