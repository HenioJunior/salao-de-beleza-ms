package com.salaobeleza.servico.dtos;

import com.salaobeleza.servico.enums.TipoServico;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Valid
public class ServicoResponse {

    private String id;
    private double valor;
    private TipoServico tipo;

}
