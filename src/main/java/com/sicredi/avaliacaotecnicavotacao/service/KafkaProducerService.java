package com.sicredi.avaliacaotecnicavotacao.service;

import com.sicredi.avaliacaotecnicavotacao.dto.PautaVotadaMsgResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaProducerService {

    private static final String TOPIC = "PAUTA_VOTADA";
    private static final String KEY = "VOTACAO";
    private final KafkaProducer<String, PautaVotadaMsgResponseDto> producer;

    public void enviarMensagem(PautaVotadaMsgResponseDto msgResponseDto) {
        ProducerRecord<String, PautaVotadaMsgResponseDto> mensagem = new ProducerRecord<>(TOPIC, KEY, msgResponseDto);
        producer.send(mensagem);
        log.info("MENSAGEM ENVIADA COM SUCESSO" + "\nTOPICO: " + mensagem.topic() + "\nKEY: " + mensagem.key() + "\nMENSAGEM: " + mensagem.value().toString());
    }


}
