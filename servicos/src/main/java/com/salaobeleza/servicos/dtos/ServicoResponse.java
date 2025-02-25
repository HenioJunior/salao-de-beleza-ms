package com.salaobeleza.servicos.dtos;

import com.salaobeleza.servicos.enums.TipoServico;
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
