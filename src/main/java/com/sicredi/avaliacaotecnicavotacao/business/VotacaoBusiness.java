package com.sicredi.avaliacaotecnicavotacao.business;

import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.VotoSessaoEntityKey;
import com.sicredi.avaliacaotecnicavotacao.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class VotacaoBusiness {

    private final SessaoService sessaoService;
    private final AssociadoService associadoService;
    private final PautaService pautaService;
    private final VotoService votoService;
    private final CpfApiValidationService cpfApiValidationService;

    public VotoSessaoEntity votar(VotoSessaoEntity votoEntity) {
        Long idAssociado = votoEntity.getAssociado().getId();
        Long idSessao = votoEntity.getSessao().getId();

        verificaVotoMultiplo(votoEntity);
        verificaSessao(votoEntity, idSessao);
        verificaAssociado(votoEntity, idAssociado);

        votoEntity.setId(
                new VotoSessaoEntityKey(votoEntity.getSessao().getId(),
                        votoEntity.getAssociado().getId()));
        atualizaVotos(votoEntity);
        return votoService.salvar(votoEntity);

    }

    private void verificaAssociado(VotoSessaoEntity votoEntity, Long idAssociado) {
        votoEntity.setAssociado(associadoService.buscarPorId(idAssociado));
        cpfApiValidationService.validaApiStatusCpf(votoEntity.getAssociado().getCpf());
    }

    private void verificaSessao(VotoSessaoEntity votoEntity, Long idSessao) {
        votoEntity.setSessao(sessaoService.buscarPorId(idSessao));
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
        } else {
            addVoto = votoEntity.getSessao().getPauta().getVotosNao();
            votoEntity.getSessao().getPauta().setVotosNao(addVoto + 1);
        }
        persisteVoto(votoEntity.getSessao().getPauta());
    }

    private void persisteVoto(PautaEntity pautaEntity) {
        pautaService.atualizar(pautaEntity.getId(), pautaEntity);
    }
}
