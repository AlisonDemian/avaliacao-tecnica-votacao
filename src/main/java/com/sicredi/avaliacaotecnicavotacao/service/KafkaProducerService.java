package com.sicredi.avaliacaotecnicavotacao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaProducerService {

    private static final String TOPIC = "PAUTA_VOTADA";
    private static final String KEY = "VOTACAO";
    private final KafkaTemplate<String, Object> producer;

    public void enviarMensagem(Object msgValue) {
        producer.send(TOPIC, KEY, msgValue);
        log.debug("MENSAGEM SENDO ENVIADA: ", msgValue.toString());
    }
}
