package com.sicredi.avaliacaotecnicavotacao.configuration;

import com.sicredi.avaliacaotecnicavotacao.configuration.serializer.KafkaValueToJsonSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfiguration {

    @Bean
    public KafkaTemplate getKafkaTemplate() {
        return new KafkaTemplate(getProperties());
    }

    @Bean
    public ProducerFactory getProperties() {
        Map<String, String> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9093");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaValueToJsonSerializer.class.getName());
        return new DefaultKafkaProducerFactory(properties);
    }
}
