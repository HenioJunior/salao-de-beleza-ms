package com.salaobeleza.agendamentos.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Valid
public class AgendamentoRequest {

    private String id;
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss", shape = JsonFormat.Shape.STRING)
    @NotNull
    private LocalDateTime horario;
    @NotBlank
    private String clienteId;
    @NotBlank
    private String profissionalId;
    @NotBlank
    private String servicoId;

}
