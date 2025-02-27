package com.salaobeleza.agenda.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoServico(
        String id,
        @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss", shape = JsonFormat.Shape.STRING)
        @NotNull
        LocalDateTime horario,
        @NotBlank
        String clienteId,
        String profissionalId,
        String servicoId
) {}
