package com.salaobeleza.agendamentos.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salaobeleza.agendamentos.dtos.ClienteResponse;
import com.salaobeleza.agendamentos.dtos.ProfissionalResponse;
import com.salaobeleza.agendamentos.dtos.ServicoResponse;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "agendamentos")
public class Agendamento {

    @Id
    private String id;
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime horario;
    private ClienteResponse clienteResponse;
    private ProfissionalResponse profissionalResponse;
    private ServicoResponse servicoResponse;

    public Agendamento(LocalDateTime horario, ClienteResponse clienteResponse, ProfissionalResponse profissionalResponse, ServicoResponse servicoResponse) {
        this.id = UUID.randomUUID().toString();
        this.horario = horario;
        this.clienteResponse = clienteResponse;
        this.profissionalResponse = profissionalResponse;
        this.servicoResponse = servicoResponse;
    }

    public String getId() {
        return id;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public ClienteResponse getClienteResponse() {
        return clienteResponse;
    }

    public void setClienteResponse(ClienteResponse clienteResponse) {
        this.clienteResponse = clienteResponse;
    }

    public ProfissionalResponse getProfissionalResponse() {
        return profissionalResponse;
    }

    public void setProfissionalResponse(ProfissionalResponse profissionalResponse) {
        this.profissionalResponse = profissionalResponse;
    }

    public ServicoResponse getServicoResponse() {
        return servicoResponse;
    }

    public void setServicoResponse(ServicoResponse servicoResponse) {
        this.servicoResponse = servicoResponse;
    }
}
