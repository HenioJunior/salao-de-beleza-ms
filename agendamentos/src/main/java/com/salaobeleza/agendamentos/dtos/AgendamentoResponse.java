package com.salaobeleza.agendamentos.dtos;


import com.salaobeleza.agendamentos.entities.Agendamento;

import java.time.LocalDateTime;

public class AgendamentoResponse {


    private String id;
    private LocalDateTime horario;
    private ClienteResponse clienteResponse;
    private ProfissionalResponse profissionalResponse;
    private ServicoResponse servicoResponse;


    public AgendamentoResponse(Agendamento agendamento) {
        this.id = agendamento.getId();
        this.horario = agendamento.getHorario();
        this.clienteResponse = agendamento.getClienteResponse();
        this.profissionalResponse= agendamento.getProfissionalResponse();
        this.servicoResponse = agendamento.getServicoResponse();
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public ClienteResponse getClienteResponse() {
        return clienteResponse;
    }

    public ProfissionalResponse getProfissionalResponse() {
        return profissionalResponse;
    }

    public ServicoResponse getServicoResponse() {
        return servicoResponse;
    }
}
