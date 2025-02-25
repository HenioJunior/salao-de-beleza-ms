package com.salaobeleza.clientes.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClienteResponse {

    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
}
