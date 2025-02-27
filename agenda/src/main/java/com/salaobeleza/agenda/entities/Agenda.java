package com.salaobeleza.agenda.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.salaobeleza.agenda.dtos.ClienteResponse;
import com.salaobeleza.agenda.dtos.ProfissionalResponse;
import com.salaobeleza.agenda.dtos.ServicoResponse;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "agendamentos")
public class Agenda {

    @Id
    private String id;
    @JsonFormat(pattern = "dd-MM-yyyy@HH:mm:ss", shape = JsonFormat.Shape.STRING)
    private LocalDateTime horario;
    private ClienteResponse cliente;
    private ProfissionalResponse profissional;
    private ServicoResponse servico;
}
