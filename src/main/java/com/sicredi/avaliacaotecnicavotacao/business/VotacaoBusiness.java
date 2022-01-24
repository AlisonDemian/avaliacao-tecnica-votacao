package com.sicredi.avaliacaotecnicavotacao.business;

import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntityKey;
import com.sicredi.avaliacaotecnicavotacao.service.AssociadoService;
import com.sicredi.avaliacaotecnicavotacao.service.PautaService;
import com.sicredi.avaliacaotecnicavotacao.service.SessaoService;
import com.sicredi.avaliacaotecnicavotacao.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class VotacaoBusiness {

    private final SessaoService sessaoService;
    private final AssociadoService associadoService;
    private final PautaService pautaService;
    private final VotoService votoService;

    public VotoSessaoEntity votar(VotoSessaoEntity votoEntity) {
        Long idAssociado = votoEntity.getAssociado().getId();
        Long idSessao = votoEntity.getSessao().getId();

        verificaVotoMultiplo(votoEntity);

        votoEntity.setSessao(sessaoService.buscarPorId(votoEntity.getSessao().getId()));
        votoEntity.setAssociado(associadoService.buscarPorId(votoEntity.getAssociado().getId()));
        votoEntity.setId(
                new VotoSessaoEntityKey(votoEntity.getSessao().getId(),
                        votoEntity.getAssociado().getId()));

        atualizaVotos(votoEntity);
        return votoService.salvar(votoEntity);

    }

    private void verificaVotoMultiplo(VotoSessaoEntity votoEntity) {
        votoService.verificaVotoMultiplo(votoEntity);

    }

    private void atualizaVotos(VotoSessaoEntity votoEntity) {
        Integer voto = votoEntity.getVoto();
        int addVoto = 0;

        if (voto.equals(1)) {
            addVoto = votoEntity.getSessao().getPauta().getVotosSim();
            votoEntity.getSessao().getPauta().setVotosSim(addVoto + 1);
            persisteVoto(votoEntity.getSessao().getPauta());
        } else {
            addVoto = votoEntity.getSessao().getPauta().getVotosNao();
            votoEntity.getSessao().getPauta().setVotosNao(addVoto + 1);
            persisteVoto(votoEntity.getSessao().getPauta());
        }
    }

    private PautaEntity persisteVoto(PautaEntity pautaEntity) {
        return pautaService.atualizar(pautaEntity.getId(), pautaEntity);
    }
}
