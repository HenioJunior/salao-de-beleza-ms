package com.salaobeleza.agendamentos.dtos;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Valid
public class AgendamentoResponse {

    private String id;
    private LocalDateTime horario;
    private String clienteId;
    private String profissionalId;
    private String servicoId;
}
