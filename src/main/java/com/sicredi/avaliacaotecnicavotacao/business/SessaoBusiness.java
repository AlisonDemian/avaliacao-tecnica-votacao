package com.sicredi.avaliacaotecnicavotacao.business;

import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.enums.PautaStatusEnum;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementAlreadyExistsException;
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

    public SessaoEntity criar(SessaoEntity entity) {
        Long idPauta = entity.getPauta().getId();
        String statusPauta = pautaService.buscarPorId(idPauta).getStatusVotacao();

        if ((Objects.equals(statusPauta, PautaStatusEnum.EM_ABERTO.status)
                && sessaoService.verificaSePautaNaoTemSessao(idPauta))) {

            entity.setPauta(pautaService.buscarPorId(idPauta));
            return sessaoService.criar(entity);
        } else {
            throw new ElementAlreadyExistsException(String.format("pauta %d ja possui sessão", idPauta));
        }
    }
}
