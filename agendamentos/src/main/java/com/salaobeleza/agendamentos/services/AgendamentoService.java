package com.salaobeleza.agendamentos.services;

import com.salaobeleza.agendamentos.dtos.*;
import com.salaobeleza.agendamentos.entities.Agendamento;
import com.salaobeleza.agendamentos.http.ApiCliente;
import com.salaobeleza.agendamentos.http.ApiProfissional;
import com.salaobeleza.agendamentos.http.ApiServico;
import com.salaobeleza.agendamentos.repositories.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ApiCliente apiCliente;

    @Autowired
    private ApiProfissional apiProfissional;

    @Autowired
    private ApiServico apiServico;

    @Transactional(readOnly = true)
    public AgendamentoResponse buscaPorId(String id) {
        Agendamento agendamento = agendamentoRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Recurso não encontrado"));
        return new AgendamentoResponse(agendamento);
    }

    @Transactional(readOnly = true)
    public Page<AgendamentoResponse> buscaTodos(Pageable pageable) {
        Page<Agendamento> result = agendamentoRepository.findAll(pageable);
        return result.map(AgendamentoResponse::new);
    }

    public AgendamentoResponse novoAgendamento(AgendamentoRequest request) {
        ClienteResponse clienteResponse = apiCliente.getCliente(request.getClienteId());
        ProfissionalResponse profissionalResponse = apiProfissional.getProfissional(request.getProfissionalId());
        ServicoResponse servicoResponse = apiServico.getServico(request.getServicoId());

        Agendamento agendamento = new Agendamento(request.getHorario(), clienteResponse, profissionalResponse, servicoResponse);
        agendamentoRepository.save(agendamento);
        return new AgendamentoResponse(agendamento);

    }

    public AgendamentoResponse atualizaAgendamento(String id, AgendamentoRequest request) {
        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não localizado"));
        agendamento.setHorario(request.getHorario());
        agendamento.setClienteResponse(apiCliente.getCliente(request.getClienteId()));
        agendamento.setProfissionalResponse(apiProfissional.getProfissional(request.getProfissionalId()));
        agendamento.setServicoResponse(apiServico.getServico(request.getServicoId()));
        agendamentoRepository.save(agendamento);
        return new AgendamentoResponse(agendamento);
    }

    public void deletaAgendamento(String id) {
        if(!agendamentoRepository.existsById(id)) {
            throw new RuntimeException("Recurso não encontrado");
        }
        try {
            agendamentoRepository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Falha de integridade referencial");
        }
    }
}

