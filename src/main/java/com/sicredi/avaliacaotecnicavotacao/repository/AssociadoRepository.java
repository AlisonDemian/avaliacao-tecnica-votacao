package com.sicredi.avaliacaotecnicavotacao.repository;

import com.sicredi.avaliacaotecnicavotacao.entity.AssociadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadoRepository extends JpaRepository<AssociadoEntity, Long> {

    @Query("SELECT COUNT(*) > 0" +
            " FROM AssociadoEntity a" +
            " WHERE a.cpf = :cpf")
    boolean buscarPorCpf(String cpf);

}
