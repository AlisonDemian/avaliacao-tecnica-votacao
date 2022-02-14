package com.sicredi.avaliacaotecnicavotacao.configuration;

import com.sicredi.avaliacaotecnicavotacao.converter.PautaConverter;
import com.sicredi.avaliacaotecnicavotacao.entity.PautaEntity;
import com.sicredi.avaliacaotecnicavotacao.entity.SessaoEntity;
import com.sicredi.avaliacaotecnicavotacao.service.KafkaProducerService;
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
public class SessaoScheduler {

    private final SessaoService sessaoService;
    private final PautaService pautaService;
    private final VotoService votoService;
    private final PautaConverter pautaConverter;
    private final KafkaProducerService kafkaProducerService;

    @Scheduled(fixedDelay = 1000)
    public void verificaSessaoStatusTask() {
        List<SessaoEntity> sessoes = sessaoService.listar();

        sessoes.stream()
                .filter(sessao -> sessao.getTempoVotacao().isEqual(LocalDateTime.now())
                        || sessao.getTempoVotacao().isBefore(LocalDateTime.now()))
                .forEach(this::atualizaPautaVotada);
    }

    private void atualizaPautaVotada(SessaoEntity sessao) {
        PautaEntity pauta = sessao.getPauta();
        Integer totalVotosSim = pauta.getVotosSim();
        Integer totalVotosNao = pauta.getVotosNao();
        votoService.deletarVotacoes(sessao.getId());

        if (totalVotosSim > totalVotosNao) {
            pautaService.atualizaPautaComSessaoEncerrada(sessao.getPauta().getId(), APROVADA.status);
        } else if (Objects.equals(totalVotosSim, totalVotosNao)) {
            pautaService.atualizaPautaComSessaoEncerrada(sessao.getPauta().getId(), EMPATE.status);
        } else {
            pautaService.atualizaPautaComSessaoEncerrada(sessao.getPauta().getId(), REPROVADA.status);
        }
        sessaoService.deletar(sessao.getId());
        enviarMensagemKafka(pauta);
    }

    private void enviarMensagemKafka(PautaEntity pauta) {
        PautaEntity pautaComStatusAtualizado = pautaService.buscarPorId(pauta.getId());
        kafkaProducerService.enviarMensagem(pautaConverter.entityToVotacaoMsgResponseDto(pautaComStatusAtualizado));
    }
}
