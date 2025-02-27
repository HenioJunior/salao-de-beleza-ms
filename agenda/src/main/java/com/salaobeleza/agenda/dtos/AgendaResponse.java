package com.salaobeleza.agenda.dtos;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Valid
public class AgendaResponse {

    private String id;
    private LocalDateTime horario;
    private ClienteResponse cliente;
    private ProfissionalResponse profissional;
    private ServicoResponse servico;
}
