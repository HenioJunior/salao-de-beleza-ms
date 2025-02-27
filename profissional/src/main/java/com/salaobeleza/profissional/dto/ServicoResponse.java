package com.salaobeleza.profissional.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ServicoResponse {

    private String id;
    private double valor;
    private String tipo;

}
