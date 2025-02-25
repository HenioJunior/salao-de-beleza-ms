package com.salaobeleza.profissionais.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProfissionalResponse {

    private String id;
    private String nome;
    private String cpf;
    private String email;
    private String telefone;
}
