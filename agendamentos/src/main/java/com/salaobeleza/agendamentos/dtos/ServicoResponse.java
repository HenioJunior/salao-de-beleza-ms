package com.salaobeleza.agendamentos.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
public class ServicoResponse {

    @Id
    private String id;
    private double valor;
    @JsonProperty(value = "tipo")
    private String tipoServico;
}
