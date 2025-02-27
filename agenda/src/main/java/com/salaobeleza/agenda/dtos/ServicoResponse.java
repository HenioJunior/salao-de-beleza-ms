package com.salaobeleza.agenda.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ServicoResponse {

    private String id;
    private double valor;
    private String tipo;
}
