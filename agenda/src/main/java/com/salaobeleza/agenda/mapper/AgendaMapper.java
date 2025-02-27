package com.salaobeleza.agenda.mapper;

import com.salaobeleza.agenda.dtos.*;
import com.salaobeleza.agenda.entities.Agenda;
import com.salaobeleza.agenda.http.ApiCliente;
import com.salaobeleza.agenda.http.ApiProfissional;
import com.salaobeleza.agenda.http.ApiServico;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AgendaMapper {

    @Autowired
    private ApiCliente apiCliente;

    @Autowired
    private ApiProfissional apiProfissional;

    @Autowired
    private ApiServico apiServico;


    public Agenda toAgendamento(DadosAgendamentoServico request) {
        return Agenda.builder()
                .horario(request.horario())
                .cliente(toClienteResponse(request.clienteId()))
                .profissional(toProfissionalResponse(request.profissionalId()))
                .servico(toServicoResponse(request.servicoId()))
                .build();
    }

    public AgendaResponse toAgendamentoResponse(Agenda agenda) {
        return new AgendaResponse(
                agenda.getId(),
                agenda.getHorario(),
                agenda.getCliente(),
                agenda.getProfissional(),
                agenda.getServico()
        );
    }

    public ClienteResponse toClienteResponse( String id) {

        return apiCliente.getCliente(id);
    }

    public ProfissionalResponse toProfissionalResponse(String id) {
        var response = apiProfissional.getProfissional(id);
        return new ProfissionalResponse(
                response.getId(),
                response.getNome(),
                response.getCpf(),
                response.getEmail(),
                response.getTelefone(),
                response.getServico()
        );
    }

    public ServicoResponse toServicoResponse(String id) {
        return apiServico.getServico(id);
    }

    public ProfissionalResponse escolherProfissionalAleatorioLivreNaData(String tipoServico, LocalDateTime horario) {
        return null;
    }
}
