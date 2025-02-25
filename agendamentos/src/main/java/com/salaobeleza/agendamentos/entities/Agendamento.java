package com.salaobeleza.agendamentos.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salaobeleza.agendamentos.dtos.ClienteResponse;
import com.salaobeleza.agendamentos.dtos.ProfissionalResponse;
import com.salaobeleza.agendamentos.dtos.ServicoResponse;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "agendamentos")
public class Agendamento {

    @Id
    private String id;
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime horario;
    private String clienteId;
    private String profissionalId;
    private String servicoId;
}
