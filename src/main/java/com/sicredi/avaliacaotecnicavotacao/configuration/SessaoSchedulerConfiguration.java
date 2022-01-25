package com.sicredi.avaliacaotecnicavotacao.configuration;

import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.service.PautaService;
import com.sicredi.avaliacaotecnicavotacao.service.SessaoService;
import com.sicredi.avaliacaotecnicavotacao.service.VotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static com.sicredi.avaliacaotecnicavotacao.enums.PautaStatusEnum.*;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class SessaoSchedulerConfiguration {

    private final SessaoService sessaoService;
    private final PautaService pautaService;
    private final VotoService votoService;

    @Scheduled(fixedDelay = 1000)
    public void verificaSessaoStatusTask() {
        List<SessaoEntity> sessoes = sessaoService.listarSessaoStatusAberto();

        sessoes.stream()
                .filter(sessao -> sessao.getTempoVotacao().isEqual(LocalDateTime.now())
                        || sessao.getTempoVotacao().isBefore(LocalDateTime.now()))
                .forEach(this::atualizaPautaVotada);
    }

    private void atualizaPautaVotada(SessaoEntity sessao) {
        Integer totalVotosSim = sessao.getPauta().getVotosSim();
        Integer totalVotosNao = sessao.getPauta().getVotosNao();
        votoService.deletarVotacoes(sessao.getId());

        if ( totalVotosSim > totalVotosNao ) {
            pautaService.atualizaPautaComSessaoEncerrada(sessao.getPauta().getId(), APROVADA.status);
        } else if ( Objects.equals(totalVotosSim, totalVotosNao )) {
            pautaService.atualizaPautaComSessaoEncerrada(sessao.getPauta().getId(), EMPATE.status);
        } else {
            pautaService.atualizaPautaComSessaoEncerrada(sessao.getPauta().getId(), REPROVADA.status);
        }
        deletarSessao(sessao);
    }

    private void deletarSessao(SessaoEntity sessao) {
        sessaoService.deletar(sessao.getId());
    }
}
