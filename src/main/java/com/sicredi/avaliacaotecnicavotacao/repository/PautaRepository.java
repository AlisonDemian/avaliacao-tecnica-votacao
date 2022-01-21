package com.sicredi.avaliacaotecnicavotacao.repository;

import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface PautaRepository extends JpaRepository<PautaEntity, Long> {

    @Transactional
    @Modifying
    @Query(value = "UPDATE PautaEntity p " +
            "SET p.statusVotacao = :status " +
            "WHERE p.id = :id")
    void atualizaPautaComSessaoEncerrada(Long id, String status);
}
