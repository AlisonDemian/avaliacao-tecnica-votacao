package com.sicredi.avaliacaotecnicavotacao.business;

import com.sicredi.avaliacaotecnicavotacao.entity.AssociadoEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.service.AssociadoService;
import com.sicredi.avaliacaotecnicavotacao.service.PautaService;
import com.sicredi.avaliacaotecnicavotacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class VotacaoBusiness {

    private final SessaoService sessaoService;
    private final AssociadoService associadoService;
    private final PautaService pautaService;

    public VotoSessaoEntity votar(VotoSessaoEntity votoEntity) {
        SessaoEntity sessao = validaSessao(votoEntity.getSessao().getId());
        AssociadoEntity associado = validaAssociado(votoEntity.getAssociado().getId());
        votoEntity.setSessao(sessao);
        votoEntity.setAssociado(associado);

        atualizaVotos(votoEntity);
        return votoEntity;
    }

    private SessaoEntity validaSessao(Long idSessao) {
        return sessaoService.buscarPorId(idSessao);
    }

    private AssociadoEntity validaAssociado(Long idAssociado) {
        return associadoService.buscarPorId(idAssociado);
    }

    private void atualizaVotos(VotoSessaoEntity votoEntity) {
        Integer voto = votoEntity.getVoto();
        int addVoto = 0;

        if (voto.equals(1)) {
            addVoto = votoEntity.getSessao().getPauta().getVotosSim();
            votoEntity.getSessao().getPauta().setVotosSim(addVoto + 1);
            persistirVoto(votoEntity.getSessao().getPauta());
        } else {
            addVoto = votoEntity.getSessao().getPauta().getVotosNao();
            votoEntity.getSessao().getPauta().setVotosNao(addVoto + 1);
            persistirVoto(votoEntity.getSessao().getPauta());
        }
    }

    private PautaEntity persistirVoto(PautaEntity pautaEntity) {
        return pautaService.atualizar(pautaEntity.getId(), pautaEntity);
    }
}
