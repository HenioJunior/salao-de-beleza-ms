package com.salaobeleza.agendamentos.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ClienteResponse {

    @JsonProperty(value = "id")
    private String id;
    @JsonProperty(value = "nome")
    private String nome;
    @JsonProperty(value = "cpf")
    private String cpf;
    @JsonProperty(value = "email")
    private String email;
    @JsonProperty(value = "telefone")
    private String telefone;
}
