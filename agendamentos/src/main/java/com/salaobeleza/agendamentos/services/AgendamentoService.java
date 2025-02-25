package com.salaobeleza.agendamentos.services;

import com.salaobeleza.agendamentos.dtos.*;
import com.salaobeleza.agendamentos.entities.Agendamento;
import com.salaobeleza.agendamentos.http.ApiCliente;
import com.salaobeleza.agendamentos.http.ApiProfissional;
import com.salaobeleza.agendamentos.http.ApiServico;
import com.salaobeleza.agendamentos.mapper.AgendamentoMapper;
import com.salaobeleza.agendamentos.repositories.AgendamentoRepository;
import com.salaobeleza.agendamentos.services.exceptions.ResourceNotFoundException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository repository;

    @Autowired
    private AgendamentoMapper mapper;

    public String novoAgendamento(AgendamentoRequest request) {
        var agendamento = repository.save(mapper.toAgendamento(request));
        return agendamento.getId();
    }

    public void atualizaAgendamento(AgendamentoRequest request) {
        var agendamento = repository.findById(request.getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Agendamento com id '%s' não encontrado", request.getId())));
        mergeAgendamento(agendamento, request);
        repository.save(agendamento);
    }

    private void mergeAgendamento(Agendamento agendamento, AgendamentoRequest request) {
        if(StringUtils.isNotBlank(request.getClienteId())) {
            agendamento.setClienteId(request.getClienteId());
        }
        if(StringUtils.isNotBlank(request.getProfissionalId())) {
            agendamento.setProfissionalId(request.getProfissionalId());
        }
        if(StringUtils.isNotBlank(request.getServicoId())) {
            agendamento.setServicoId(request.getServicoId());
        }
    }

    @Transactional(readOnly = true)
    public AgendamentoResponse buscaPorId(String id) {
        return repository.findById(id)
                .map(mapper::toAgendamentoResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Agendamento com id '%s' não encontrado", id)));
    }

    @Transactional(readOnly = true)
    public List<AgendamentoResponse> buscaTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toAgendamentoResponse)
                .collect(Collectors.toList());
    }

    public void deletaAgendamento(String id) {
        if(!repository.existsById(id)) {
            throw new RuntimeException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Falha de integridade referencial");
        }
    }
}

