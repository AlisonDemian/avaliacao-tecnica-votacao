package com.sicredi.avaliacaotecnicavotacao.repository;

import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VotoSessaoRepository extends JpaRepository<VotoSessaoEntity, Long> {
}
