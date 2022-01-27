package com.sicredi.avaliacaotecnicavotacao.service;

import com.sicredi.avaliacaotecnicavotacao.dto.CpfVerificationResponseDto;
import com.sicredi.avaliacaotecnicavotacao.entity.AssociadoEntity;
import com.sicredi.avaliacaotecnicavotacao.exception.BusinessGenericException;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementAlreadyExistsException;
import com.sicredi.avaliacaotecnicavotacao.exception.ElementNotFoundException;
import com.sicredi.avaliacaotecnicavotacao.repository.AssociadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class AssociadoService {

    private static final String urlApiVerificaCpf = "https://user-info.herokuapp.com/users/";
    private static final String STATUS_CPF_IMPOSSIBILITADO = "UNABLE_TO_VOTE";
    private final AssociadoRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    public AssociadoEntity criar(AssociadoEntity entity) {
        validaApiStatusCpf(entity.getCpf());
        buscarPorCpf(entity.getCpf());
        return repository.save(entity);
    }

    public List<AssociadoEntity> listar() {
        return repository.findAll();
    }

    public AssociadoEntity buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException(String.format("Associado com id %d não encontrado", id)));
    }

    public void buscarPorCpf(String cpf) {

        if (repository.buscarPorCpf(cpf)) {
            throw new ElementAlreadyExistsException(String.format("Associado com o CPF %s já registrado", cpf));
        }
    }

    public AssociadoEntity atualizar(Long id, AssociadoEntity entityAtualizada) {
        validaApiStatusCpf(entityAtualizada.getCpf());
        buscarPorId(id);
        buscarPorCpf(entityAtualizada.getCpf());
        entityAtualizada.setId(id);
        return repository.save(entityAtualizada);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

    public void validaApiStatusCpf(String cpf) {
        String urlCpf = String.format("%s%s", urlApiVerificaCpf, cpf);
        ResponseEntity<CpfVerificationResponseDto> statusCpfResponse = restTemplate.getForEntity(urlCpf,
                CpfVerificationResponseDto.class);

        if (Objects.equals(statusCpfResponse.getBody().getStatus(), STATUS_CPF_IMPOSSIBILITADO)) {
            throw new BusinessGenericException(String.format("CPF %s está impossibilitado de votar", cpf));
        }
    }
}
