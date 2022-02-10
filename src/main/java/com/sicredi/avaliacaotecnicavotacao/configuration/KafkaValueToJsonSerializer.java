package com.sicredi.avaliacaotecnicavotacao.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.kafka.common.serialization.Serializer;

public class KafkaValueToJsonSerializer<T> implements Serializer<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public byte[] serialize(String s, Object dto) {
        return objectMapper.writeValueAsBytes(dto);
    }
}
