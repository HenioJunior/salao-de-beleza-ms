package com.salaobeleza.agenda.services;

import com.salaobeleza.agenda.dtos.*;
import com.salaobeleza.agenda.entities.Agenda;
import com.salaobeleza.agenda.exceptions.ValidacaoException;
import com.salaobeleza.agenda.mapper.AgendaMapper;
import com.salaobeleza.agenda.repositories.AgendaRepository;
import com.salaobeleza.agenda.exceptions.ResourceNotFoundException;
import com.salaobeleza.agenda.validacoes.ValidadorAgendamento;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository repository;

    @Autowired
    private AgendaMapper mapper;

    @Autowired
    private List<ValidadorAgendamento> validadores;

    public AgendaResponse agendar(DadosAgendamentoServico dados) {

        var cliente = mapper.toClienteResponse(dados.clienteId());
        var profissional = mapper.toProfissionalResponse(dados.profissionalId());
        ServicoResponse servico = new ServicoResponse();
        if(StringUtils.isNotBlank(dados.servicoId())) {
           servico = mapper.toServicoResponse(dados.servicoId());
       }

        validadores.forEach(v -> v.validar(dados));

        var agenda = new Agenda(UUID.randomUUID().toString(),
                dados.horario(),
                cliente,
                profissional,
                servico
        );
        repository.save(agenda);

        return mapper.toAgendamentoResponse(agenda);
    }

    private ProfissionalResponse escolherProfissional(DadosAgendamentoServico dados) {
        if(StringUtils.isNotBlank(dados.profissionalId())) {
            return mapper.toProfissionalResponse(dados.profissionalId());
        }
        if(StringUtils.isBlank(dados.servicoId())) {
            throw new ValidacaoException("Tipo de Serviço é obrigatório quando um profissional não for escolhido");
        }
        return mapper.escolherProfissionalAleatorioLivreNaData(dados.servicoId(), dados.horario());
    }

    public void atualizaAgendamento(DadosAgendamentoServico request) {
        var agendamento = repository.findById(request.id())
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Agendamento com id '%s' não encontrado", request.id())));
        mergeAgendamento(agendamento, request);
        repository.save(agendamento);
    }

    private void mergeAgendamento(Agenda agenda, DadosAgendamentoServico request) {
        if(StringUtils.isNotBlank(request.clienteId())) {
            agenda.setCliente(mapper.toClienteResponse(request.clienteId()));
        }
        if(StringUtils.isNotBlank(request.profissionalId())) {
            agenda.setProfissional(mapper.toProfissionalResponse(request.profissionalId()));
        }
    }

    @Transactional(readOnly = true)
    public AgendaResponse buscaPorId(String id) {
        return repository.findById(id)
                .map(mapper::toAgendamentoResponse)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Agendamento com id '%s' não encontrado", id)));
    }

    @Transactional(readOnly = true)
    public List<AgendaResponse> buscaTodos() {
        return repository.findAll()
                .stream()
                .map(mapper::toAgendamentoResponse)
                .collect(Collectors.toList());
    }

    public void excluiAgendamento(String id) {
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

