package com.salaobeleza.agendamentos.mapper;

import com.salaobeleza.agendamentos.dtos.*;
import com.salaobeleza.agendamentos.entities.Agendamento;
import com.salaobeleza.agendamentos.http.ApiCliente;
import com.salaobeleza.agendamentos.http.ApiProfissional;
import com.salaobeleza.agendamentos.http.ApiServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgendamentoMapper {

    @Autowired
    private ApiCliente apiCliente;

    @Autowired
    private ApiProfissional apiProfissional;

    @Autowired
    private ApiServico apiServico;


    public Agendamento toAgendamento(AgendamentoRequest request) {
        ClienteResponse clienteResponse = apiCliente.getCliente(request.getClienteId());
        ProfissionalResponse profissionalResponse = apiProfissional.getProfissional(request.getProfissionalId());
        ServicoResponse servicoResponse = apiServico.getServico(request.getServicoId());
        return Agendamento.builder()
                .horario(request.getHorario())
                .clienteId(clienteResponse.getId())
                .profissionalId(profissionalResponse.getId())
                .servicoId(servicoResponse.getId())
                .build();
    }

    public AgendamentoResponse toAgendamentoResponse(Agendamento agendamento) {
        return new AgendamentoResponse(
                agendamento.getId(),
                agendamento.getHorario(),
                agendamento.getClienteId(),
                agendamento.getProfissionalId(),
                agendamento.getServicoId());
    }
}
