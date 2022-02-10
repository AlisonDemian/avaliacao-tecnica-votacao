package com.sicredi.avaliacaotecnicavotacao.configuration;

import com.sicredi.avaliacaotecnicavotacao.dto.PautaVotadaMsgResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class KafkaConfiguration {

    private static final String TOPICO_VOTACAO_FINALIZADA = "VOTACAO_PAUTA_FINALIZADA";
    private static final String KEY = "votacao";

    public void enviaMensagem(PautaVotadaMsgResponseDto pautaVotadaDto) {
        KafkaProducer producer = new KafkaProducer(geraProducerProperties());
        ProducerRecord mensagem = new ProducerRecord<>(TOPICO_VOTACAO_FINALIZADA, KEY, pautaVotadaDto);
        producer.send(mensagem, (dados, ex) -> {
            if (ex != null) throw new RuntimeException("Erro ao mandar mensagem");
            else {
                String msgLog = "MENSAGEM DO TOPICO: " + dados.topic() + "FOI ENVIADA";
                log.info(msgLog);
            }
        });
    }

    private Properties geraProducerProperties() {
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaValueToJsonSerializer.class.getName());
        return properties;
    }
}
