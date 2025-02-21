package com.salaobeleza.agendamentos.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class AgendamentoRequest {

    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @NotNull
    private LocalDateTime horario;
    @NotBlank
    private String clienteId;
    @NotBlank
    private String profissionalId;
    @NotBlank
    private String servicoId;

    public AgendamentoRequest() {}

    public AgendamentoRequest(LocalDateTime horario, String clienteId, String profissionalId, String servicoId) {
        this.horario = horario;
        this.clienteId = clienteId;
        this.profissionalId = profissionalId;
        this.servicoId = servicoId;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public String getClienteId() {
        return clienteId;
    }

    public String getProfissionalId() {
        return profissionalId;
    }

    public String getServicoId() {
        return servicoId;
    }
}
