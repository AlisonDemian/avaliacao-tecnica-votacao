package com.sicredi.avaliacaotecnicavotacao.configuration;

import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.service.PautaService;
import com.sicredi.avaliacaotecnicavotacao.service.SessaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Configuration
@EnableScheduling
public class SessaoSchedulerConfiguration {

    private static final String STATUS_REPROVADA = "reprovada";
    private static final String STATUS_APROVADA = "aprovada";
    private static final String STATUS_EMPATE = "empate";
    private final SessaoService sessaoService;
    private final PautaService pautaService;

    @Scheduled(fixedDelay = 1000)
    public void verificaSessaoStatusTask() {
        List<SessaoEntity> sessoes = sessaoService.listarSessaoStatusAberto();

        sessoes.stream()
                .filter(sessao -> sessao.getTempoVotacao().isEqual(LocalDateTime.now())
                        || sessao.getTempoVotacao().isBefore(LocalDateTime.now()))
                .forEach(sessao -> atualizaPautaVotada(sessao));
    }

    private void atualizaPautaVotada(SessaoEntity sessao) {
        Integer totalVotosSim = sessao.getPauta().getVotosSim();
        Integer totalVotosNao = sessao.getPauta().getVotosNao();

        if (totalVotosSim > totalVotosNao) {
            pautaService.atualizaPautaComSessaoEncerrada(sessao.getPauta().getId(), STATUS_APROVADA);
            deletarSessao(sessao);
        } else if (Objects.equals(totalVotosSim, totalVotosNao)) {
            pautaService.atualizaPautaComSessaoEncerrada(sessao.getPauta().getId(), STATUS_EMPATE);
            deletarSessao(sessao);
        } else {
            pautaService.atualizaPautaComSessaoEncerrada(sessao.getPauta().getId(), STATUS_REPROVADA);
            deletarSessao(sessao);
        }
    }

    private void deletarSessao(SessaoEntity sessao) {
        sessaoService.deletar(sessao.getId());
    }
}
