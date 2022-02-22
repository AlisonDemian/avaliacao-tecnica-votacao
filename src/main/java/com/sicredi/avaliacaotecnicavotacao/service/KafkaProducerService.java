package com.sicredi.avaliacaotecnicavotacao.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, Object> producer;

    public void enviarMensagem(String topico, Object msgValue) {
        String key = topico + RandomUtils.nextLong(1, 1000000);

        log.info("MENSAGEM SENDO ENVIADA: " + msgValue.toString());
        producer.send(topico, key, msgValue);
    }
}
