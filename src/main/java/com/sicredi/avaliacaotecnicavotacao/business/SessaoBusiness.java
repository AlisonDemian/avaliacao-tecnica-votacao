package com.sicredi.avaliacaotecnicavotacao.business;


import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.enums.PautaStatusEnum;
import com.sicredi.avaliacaotecnicavotacao.exception.BusinessGenericException;
import com.sicredi.avaliacaotecnicavotacao.service.PautaService;
import com.sicredi.avaliacaotecnicavotacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class SessaoBusiness {

    private final PautaService pautaService;
    private final SessaoService sessaoService;

    public SessaoEntity criar(SessaoEntity sessaoEntity) {
        Long idPauta = sessaoEntity.getPauta().getId();

        PautaEntity pautaEntity = pautaService.buscarPorId(idPauta);
        sessaoService.verificaSePautaNaoTemSessao(idPauta);
        verificaSePautaEstaAberta(pautaEntity, sessaoEntity);
        return sessaoService.criar(sessaoEntity);

    }

    private void verificaSePautaEstaAberta(PautaEntity pautaEntity, SessaoEntity sessaoEntity) {
        String statusPauta = pautaEntity.getStatusVotacao();
        if (Objects.equals(statusPauta, PautaStatusEnum.EM_ABERTO.status)) {
            sessaoEntity.setPauta(pautaEntity);
        } else {
            throw new BusinessGenericException(String.format("pauta %d ja foi votada", pautaEntity.getId()));
        }
    }
}