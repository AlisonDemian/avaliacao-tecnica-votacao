package com.sicredi.avaliacaotecnicavotacao.service;

import com.sicredi.avaliacaotecnicavotacao.dto.CpfVerificationResponseDto;
import com.sicredi.avaliacaotecnicavotacao.exception.BusinessGenericException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Service
public class CpfApiValidationService {

    private static final String urlApiVerificaCpf = "https://user-info.herokuapp.com/users/";
    private static final String STATUS_CPF_IMPOSSIBILITADO = "UNABLE_TO_VOTE";
    private final RestTemplate restTemplate = new RestTemplate();

    public void validaApiStatusCpf(String cpf) {
        String urlCpf = String.format("%s%s", urlApiVerificaCpf, cpf);
        ResponseEntity<CpfVerificationResponseDto> statusCpfResponse = restTemplate.getForEntity(urlCpf,
                CpfVerificationResponseDto.class);

        if (Objects.equals(statusCpfResponse.getBody().getStatus(), STATUS_CPF_IMPOSSIBILITADO)) {
            throw new BusinessGenericException(String.format("CPF %s está impossibilitado de votar", cpf));
        }
    }
}
